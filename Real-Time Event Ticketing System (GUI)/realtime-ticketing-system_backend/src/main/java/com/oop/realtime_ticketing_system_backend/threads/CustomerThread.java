package com.oop.realtime_ticketing_system_backend.threads;

import com.oop.realtime_ticketing_system_backend.model.Ticket;
import com.oop.realtime_ticketing_system_backend.service.LogService;
import com.oop.realtime_ticketing_system_backend.service.TicketPoolService;

import java.util.function.BooleanSupplier;

public class CustomerThread implements Runnable {
    private final String customerId;
    private final int retrievalRate;
    private final TicketPoolService ticketPool;
    private final LogService log;
    private final BooleanSupplier shouldRun;
    private final BooleanSupplier shouldAutoStop;

    public CustomerThread(String customerId, int retrievalRate, TicketPoolService ticketPool, LogService log,
                          BooleanSupplier shouldRun, BooleanSupplier shouldAutoStop) {
        this.customerId = customerId;
        this.retrievalRate = retrievalRate;
        this.ticketPool = ticketPool;
        this.log = log;
        this.shouldRun = shouldRun;
        this.shouldAutoStop = shouldAutoStop;
    }

    @Override
    public void run() {
        try {
            while (shouldRun.getAsBoolean()) {
                for (int i = 0; i < retrievalRate; i++) {
                    Ticket ticket = ticketPool.removeTicket();
                    if (ticket != null) {
                        log.info(customerId + " bought ticket " + ticket.getId());
                    }
                }
                Thread.sleep(1000);
                // Stop when vendors are done and pool is empty
                if (shouldAutoStop.getAsBoolean()) break;
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
