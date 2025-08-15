// src/services/websocket.js
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

const WS_BASE =
  (import.meta.env.VITE_API_URL || "http://localhost:8080") + "/ws";

/**
 * Create and activate a STOMP client that uses SockJS under the hood.
 * callbacks: { onStatus: fn, onLog: fn, onError: fn }
 * Returns the client instance (call client.deactivate() to disconnect).
 */
export function createStompClient(callbacks = {}) {
  const {
    onStatus = () => {},
    onLog = () => {},
    onError = () => {},
  } = callbacks;

  const client = new Client({
    webSocketFactory: () => new SockJS(WS_BASE),
    reconnectDelay: 5000,
    heartbeatIncoming: 4000,
    heartbeatOutgoing: 4000,
    onConnect: () => {
      // subscribe to server topics
      client.subscribe("/topic/status", (msg) => {
        try {
          onStatus(JSON.parse(msg.body));
        } catch (e) {
          console.error(e);
        }
      });
      client.subscribe("/topic/logs", (msg) => {
        try {
          onLog(JSON.parse(msg.body));
        } catch (e) {
          console.error(e);
        }
      });
    },
    onStompError: (frame) => {
      console.error("STOMP error", frame);
      onError(frame);
    },
  });

  client.activate();
  return client;
}

export function disconnectClient(client) {
  if (!client) return;
  try {
    client.deactivate(); // gracefully disconnect
  } catch (e) {
    console.warn("Error disconnecting STOMP client", e);
  }
}
