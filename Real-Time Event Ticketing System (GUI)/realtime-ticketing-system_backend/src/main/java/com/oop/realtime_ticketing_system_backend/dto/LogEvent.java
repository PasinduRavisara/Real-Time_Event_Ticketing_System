package com.oop.realtime_ticketing_system_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LogEvent {
    private LocalDateTime timestamp;
    private String level;   // INFO/WARN/ERROR
    private String message;
}
