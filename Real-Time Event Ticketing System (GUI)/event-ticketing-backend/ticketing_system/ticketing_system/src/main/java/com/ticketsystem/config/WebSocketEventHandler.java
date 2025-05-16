package com.ticketsystem.config; // Define the package for the WebSocket event handler class

import org.springframework.web.socket.WebSocketSession; // Import WebSocketSession for handling WebSocket sessions
import org.springframework.web.socket.handler.TextWebSocketHandler; // Import TextWebSocketHandler for handling text messages
import org.springframework.web.socket.TextMessage; // Import TextMessage for sending and receiving text messages

public class WebSocketEventHandler extends TextWebSocketHandler { // Extend TextWebSocketHandler to handle WebSocket text messages
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception { // Override method to handle incoming text messages
        String receivedContent = message.getPayload(); // Get the payload (content) of the received message
        System.out.println("New message received: " + receivedContent); // Print the received message to the console
        session.sendMessage(new TextMessage("Server received: " + receivedContent)); // Send a confirmation message back to the client
    }
}
