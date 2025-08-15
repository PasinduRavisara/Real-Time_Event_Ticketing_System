package com.oop.realtime_ticketing_system_backend.service;




import com.oop.realtime_ticketing_system_backend.dto.StatusResponse;
import com.oop.realtime_ticketing_system_backend.threads.CustomerThread;
import com.oop.realtime_ticketing_system_backend.threads.VendorThread;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SimulationService {
    private final TicketPoolService ticketPool;
    private final LogService log;
    private final UpdatePublisher publisher;

    private final List<Thread> workerThreads = new ArrayList<>();
    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor(new CustomizableThreadFactory("status-pusher-"));

    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicInteger activeVendors = new AtomicInteger(0);
    private final AtomicInteger activeCustomers = new AtomicInteger(0);

    public SimulationService(TicketPoolService ticketPool, LogService log, UpdatePublisher publisher) {
        this.ticketPool = ticketPool;
        this.log = log;
        this.publisher = publisher;
        // periodic status broadcast
        scheduler.scheduleAtFixedRate(this::broadcastStatus, 0, 500, TimeUnit.MILLISECONDS);
    }

    public synchronized void startSimulation(int vendors, int customers, int releaseRate, int retrievalRate,
                                             int totalTicketsPerVendor, int maxCapacity) {
        stopSimulation(); // clean slate
        ticketPool.reset();

        running.set(true);
        activeVendors.set(0);
        activeCustomers.set(0);

        log.info("Starting simulation: vendors=" + vendors + ", customers=" + customers +
                ", releaseRate=" + releaseRate + ", retrievalRate=" + retrievalRate +
                ", totalTicketsPerVendor=" + totalTicketsPerVendor + ", maxCapacity=" + maxCapacity);

        // Vendors
        for (int i = 1; i <= vendors; i++) {
            final int vendorId = i;
            VendorThread v = new VendorThread("Vendor-" + vendorId, releaseRate, totalTicketsPerVendor, maxCapacity,
                    ticketPool, log, () -> running.get(), () -> {
                int left = activeVendors.decrementAndGet();
                log.info("Vendor-" + vendorId + " finished. Active vendors=" + left);
            });
            Thread t = new Thread(v, "Vendor-" + vendorId);
            workerThreads.add(t);
            activeVendors.incrementAndGet();
            t.start();
        }

        // Customers
        for (int i = 1; i <= customers; i++) {
            CustomerThread c = new CustomerThread("Customer-" + i, retrievalRate, ticketPool, log,
                    () -> running.get(),
                    // stop condition when vendors done and pool empty
                    () -> activeVendors.get() == 0 && ticketPool.getTicketCount() == 0);
            Thread t = new Thread(c, "Customer-" + i);
            workerThreads.add(t);
            activeCustomers.incrementAndGet();
            t.start();
        }
    }

    public synchronized void stopSimulation() {
        if (!running.get()) {
            // still interrupt any lingering threads
            workerThreads.forEach(Thread::interrupt);
            workerThreads.clear();
            return;
        }
        running.set(false);
        log.warn("Stopping simulation...");
        workerThreads.forEach(Thread::interrupt);
        workerThreads.clear();
        activeVendors.set(0);
        activeCustomers.set(0);
        broadcastStatus();
    }

    private void broadcastStatus() {
        StatusResponse status = new StatusResponse(
                ticketPool.getTicketCount(),
                activeVendors.get(),
                activeCustomers.get(),
                running.get()
        );
        publisher.pushStatus(status);
    }
}
