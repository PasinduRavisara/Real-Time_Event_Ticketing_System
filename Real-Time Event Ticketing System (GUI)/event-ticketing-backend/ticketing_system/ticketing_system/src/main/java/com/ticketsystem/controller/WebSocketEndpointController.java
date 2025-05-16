package com.ticketsystem.controller; // Define the package for the controller class

import org.springframework.stereotype.Controller; // Import Controller annotation for Spring MVC controller
import org.springframework.web.bind.annotation.GetMapping; // Import GetMapping annotation for handling GET requests

@Controller // Indicate that this class is a Spring MVC controller
public class WebSocketEndpointController {
    @GetMapping("/websocket") // Map GET requests to /websocket
    public String getWebSocketEndpoint() { // Define method to handle the GET request
        return "WebSocket connection point"; // Return a string indicating the WebSocket connection point
    }
}
