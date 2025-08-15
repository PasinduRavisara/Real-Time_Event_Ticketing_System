package com.oop.realtime_ticketing_system_backend.controller;


import com.oop.realtime_ticketing_system_backend.dto.StatusResponse;
import com.oop.realtime_ticketing_system_backend.service.SimulationService;
import com.oop.realtime_ticketing_system_backend.service.LogService;
import com.oop.realtime_ticketing_system_backend.service.TicketPoolService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SimulationController {
    private final SimulationService simulation;
    private final TicketPoolService pool;

    public SimulationController(SimulationService simulation, TicketPoolService pool) {
        this.simulation = simulation;
        this.pool = pool;
    }

    @PostMapping("/start")
    public String start(@RequestParam int vendors,
                        @RequestParam int customers,
                        @RequestParam int releaseRate,
                        @RequestParam int retrievalRate,
                        @RequestParam int totalTicketsPerVendor,
                        @RequestParam int maxCapacity) {
        simulation.startSimulation(vendors, customers, releaseRate, retrievalRate, totalTicketsPerVendor, maxCapacity);
        return "Simulation started";
    }

    @PostMapping("/stop")
    public String stop() {
        simulation.stopSimulation();
        return "Simulation stopped";
    }

    @GetMapping("/status")
    public StatusResponse status() {
        // The scheduled broadcaster already pushes; this is for initial fetch/poll fallback
        return new StatusResponse(pool.getTicketCount(), 0, 0, true);
    }
}
