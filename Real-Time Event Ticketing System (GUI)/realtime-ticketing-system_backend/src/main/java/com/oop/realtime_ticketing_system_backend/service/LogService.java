package com.oop.realtime_ticketing_system_backend.service;


import com.oop.realtime_ticketing_system_backend.dto.LogEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {
    private final ArrayDeque<LogEvent> buffer = new ArrayDeque<>();
    private final int capacity = 500;

    public synchronized void info(String msg) { add("INFO", msg); }
    public synchronized void warn(String msg) { add("WARN", msg); }
    public synchronized void error(String msg){ add("ERROR", msg); }

    private void add(String level, String msg) {
        if (buffer.size() >= capacity) buffer.removeFirst();
        buffer.addLast(new LogEvent(LocalDateTime.now(), level, msg));
    }

    public synchronized List<LogEvent> recent(int max) {
        return new ArrayList<>(buffer).subList(Math.max(0, buffer.size() - max), buffer.size());
    }
}
