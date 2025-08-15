package com.oop.realtime_ticketing_system_backend.threads;

import com.oop.realtime_ticketing_system_backend.model.Ticket;
import com.oop.realtime_ticketing_system_backend.service.LogService;
import com.oop.realtime_ticketing_system_backend.service.TicketPoolService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BooleanSupplier;

public class VendorThread implements Runnable {
    private static final AtomicLong ticketIdCounter = new AtomicLong(1);

    private final String vendorId;
    private final int releaseRate;
    private final int totalTickets;
    private final int maxCapacity;
    private final TicketPoolService ticketPool;
    private final LogService log;
    private final BooleanSupplier shouldRun;
    private final Runnable onFinish;

    public VendorThread(String vendorId, int releaseRate, int totalTickets, int maxCapacity,
                        TicketPoolService ticketPool, LogService log,
                        BooleanSupplier shouldRun, Runnable onFinish) {
        this.vendorId = vendorId;
        this.releaseRate = releaseRate;
        this.totalTickets = totalTickets;
        this.maxCapacity = maxCapacity;
        this.ticketPool = ticketPool;
        this.log = log;
        this.shouldRun = shouldRun;
        this.onFinish = onFinish;
    }

    @Override
    public void run() {
        int produced = 0;
        try {
            while (shouldRun.getAsBoolean() && produced < totalTickets) {
                List<Ticket> batch = new ArrayList<>();
                for (int i = 0; i < releaseRate && produced < totalTickets; i++) {
                    batch.add(new Ticket(ticketIdCounter.getAndIncrement(), LocalDateTime.now(), vendorId));
                    produced++;
                }
                if (!batch.isEmpty()) {
                    ticketPool.addTickets(batch, maxCapacity);
                    log.info(vendorId + " released " + batch.size() + " tickets (total produced=" + produced + ")");
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        } finally {
            onFinish.run();
        }
    }
}
