// src/components/ControlPanel.jsx
import React from "react";
import { postStop } from "../services/api";

export default function ControlPanel({ running }) {
  const stop = async () => {
    try {
      await postStop();
    } catch (e) {
      console.error(e);
      alert("Failed to stop simulation");
    }
  };

  return (
    <div className="card">
      <h3>Control</h3>
      <div>
        <button onClick={stop} disabled={!running}>
          Stop Simulation
        </button>
      </div>
      <div style={{ marginTop: 8 }}>
        Status: <strong>{running ? "Running" : "Stopped"}</strong>
      </div>
    </div>
  );
}
