package com.oop.realtime_ticketing_system_backend.controller;


import com.oop.realtime_ticketing_system_backend.dto.LogEvent;
import com.oop.realtime_ticketing_system_backend.service.LogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LogController {
    private final LogService log;

    public LogController(LogService log) {
        this.log = log;
    }

    @GetMapping("/logs")
    public List<LogEvent> recent(@RequestParam(defaultValue = "200") int max) {
        return log.recent(max);
    }
}
