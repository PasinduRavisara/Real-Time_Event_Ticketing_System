package com.ticketsystem.config; // Define the package for the configuration class

import org.springframework.context.annotation.Configuration; // Import Configuration annotation for Spring configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket; // Import EnableWebSocket annotation for WebSocket support
import org.springframework.web.socket.config.annotation.WebSocketConfigurer; // Import WebSocketConfigurer interface for WebSocket configuration
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry; // Import WebSocketHandlerRegistry for registering handlers

@Configuration // Indicate that this class is a Spring configuration class
@EnableWebSocket // Enable WebSocket support in the application
public class WebSocketConfiguration implements WebSocketConfigurer { // Implement WebSocketConfigurer interface
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) { // Override method to register WebSocket handlers
        registry.addHandler(new WebSocketEventHandler(), "/websocket") // Add WebSocket handler for the specified endpoint
                .setAllowedOrigins("*"); // Allow all origins for cross-origin requests
    }
}
