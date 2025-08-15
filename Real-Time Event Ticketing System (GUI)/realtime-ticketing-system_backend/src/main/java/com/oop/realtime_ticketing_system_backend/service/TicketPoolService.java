package com.oop.realtime_ticketing_system_backend.service;


import com.oop.realtime_ticketing_system_backend.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TicketPoolService {
    private final List<Ticket> tickets = new LinkedList<>();
    private final LogService logService;
    private final UpdatePublisher publisher;

    public TicketPoolService(LogService logService, UpdatePublisher publisher) {
        this.logService = logService;
        this.publisher = publisher;
    }

    public synchronized void addTickets(List<Ticket> newTickets, int maxCapacity) {
        while (tickets.size() + newTickets.size() > maxCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        tickets.addAll(newTickets);
        logService.info("Added " + newTickets.size() + " tickets. Pool=" + tickets.size());
        notifyAll();
    }

    public synchronized Ticket removeTicket() {
        while (tickets.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        Ticket ticket = tickets.remove(0);
        notifyAll();
        return ticket;
    }

    public synchronized int getTicketCount() {
        return tickets.size();
    }

    public synchronized void reset() {
        tickets.clear();
        notifyAll();
    }
}
