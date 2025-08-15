package com.oop.realtime_ticketing_system_backend.service;


import com.oop.realtime_ticketing_system_backend.dto.LogEvent;
import com.oop.realtime_ticketing_system_backend.dto.StatusResponse;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class UpdatePublisher {
    private final SimpMessagingTemplate ws;

    public UpdatePublisher(SimpMessagingTemplate ws) {
        this.ws = ws;
    }

    public void pushStatus(StatusResponse status) {
        ws.convertAndSend("/topic/status", status);
    }

    public void pushLog(LogEvent event) {
        ws.convertAndSend("/topic/logs", event);
    }
}
