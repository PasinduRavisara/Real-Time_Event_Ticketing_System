// src/pages/Dashboard.jsx
import React from "react";
import ConfigForm from "../components/ConfigForm";
import ControlPanel from "../components/ControlPanel";
import TicketDisplay from "../components/TicketDisplay";
import LogViewer from "../components/LogViewer";

export default function Dashboard({ status, logs, onStarted }) {
  return (
    <div className="grid">
      <div className="column">
        <ConfigForm onStarted={onStarted} />
        <ControlPanel running={status?.running} />
      </div>

      <div className="column">
        <TicketDisplay status={status} />
        <LogViewer logs={logs} />
      </div>
    </div>
  );
}
