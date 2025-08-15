package com.oop.realtime_ticketing_system_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusResponse {
    private int ticketCount;
    private int activeVendors;
    private int activeCustomers;
    private boolean running;
}
