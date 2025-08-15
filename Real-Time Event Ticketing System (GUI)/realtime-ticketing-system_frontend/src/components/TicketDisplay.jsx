// src/components/TicketDisplay.jsx
import React from "react";

export default function TicketDisplay({ status }) {
  if (!status) return <div className="card">No status yet</div>;

  const { ticketCount, activeVendors, activeCustomers, running } = status;

  return (
    <div className="card">
      <h3>Ticket Pool</h3>
      <div>
        Tickets available: <strong>{ticketCount}</strong>
      </div>
      <div>Active vendors: {activeVendors}</div>
      <div>Active customers: {activeCustomers}</div>
      <div>Running: {running ? "Yes" : "No"}</div>
    </div>
  );
}
