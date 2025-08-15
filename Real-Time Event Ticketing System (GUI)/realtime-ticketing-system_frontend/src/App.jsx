// src/App.jsx
import React, { useEffect, useRef, useState } from "react";
import Dashboard from "./pages/Dashboard";
import { createStompClient, disconnectClient } from "./services/websocket";
import { getStatus, getLogs } from "./services/api";
import.meta.env.VITE_API_URL;

function App() {
  const [status, setStatus] = useState(null);
  const [logs, setLogs] = useState([]);
  const stompRef = useRef(null);

  useEffect(() => {
    let mounted = true;

    // fetch initial state (fallback)
    getStatus()
      .then((res) => {
        if (mounted) setStatus(res.data);
      })
      .catch(console.warn);
    getLogs(200)
      .then((res) => {
        if (mounted) setLogs(res.data || []);
      })
      .catch(console.warn);

    // create websocket
    stompRef.current = createStompClient({
      onStatus: (s) => {
        if (mounted) setStatus(s);
      },
      onLog: (l) => {
        if (mounted) setLogs((prev) => [...prev, l]);
      },
      onError: (err) => console.error("WS error", err),
    });

    return () => {
      mounted = false;
      disconnectClient(stompRef.current);
      stompRef.current = null;
    };
  }, []);

  return (
    <div style={{ padding: 16 }}>
      <h1>Real-time Ticketing — Dashboard</h1>
      <Dashboard
        status={status}
        logs={logs}
        onStarted={() => {
          /* no-op */
        }}
      />
    </div>
  );
}

export default App;
