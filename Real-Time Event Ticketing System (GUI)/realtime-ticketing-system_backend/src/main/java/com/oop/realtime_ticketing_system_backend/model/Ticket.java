package com.oop.realtime_ticketing_system_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Ticket {
    private long id;
    private LocalDateTime timestamp;
    private String vendorId;
}