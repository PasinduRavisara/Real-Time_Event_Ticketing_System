package com.oop.realtime_ticketing_system_backend.model;

import lombok.Data;

@Data
public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
}
