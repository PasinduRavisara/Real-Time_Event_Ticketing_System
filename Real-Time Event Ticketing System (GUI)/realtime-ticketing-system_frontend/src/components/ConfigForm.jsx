// src/components/ConfigForm.jsx
import React, { useState } from "react";
import { postStart } from "../services/api";

export default function ConfigForm({ onStarted }) {
  const [vendors, setVendors] = useState(2);
  const [customers, setCustomers] = useState(3);
  const [releaseRate, setReleaseRate] = useState(2);
  const [retrievalRate, setRetrievalRate] = useState(2);
  const [totalTicketsPerVendor, setTotalTicketsPerVendor] = useState(50);
  const [maxCapacity, setMaxCapacity] = useState(200);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const start = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    try {
      await postStart({
        vendors,
        customers,
        releaseRate,
        retrievalRate,
        totalTicketsPerVendor,
        maxCapacity,
      });
      onStarted && onStarted();
    } catch (err) {
      console.error(err);
      setError(
        err?.response?.data || err.message || "Failed to start simulation"
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={start} className="card">
      <h3>Simulation Configuration</h3>
      <label>
        Vendors
        <input
          type="number"
          min="1"
          value={vendors}
          onChange={(e) => setVendors(+e.target.value)}
        />
      </label>
      <label>
        Customers
        <input
          type="number"
          min="1"
          value={customers}
          onChange={(e) => setCustomers(+e.target.value)}
        />
      </label>
      <label>
        Release rate (tickets / second)
        <input
          type="number"
          min="1"
          value={releaseRate}
          onChange={(e) => setReleaseRate(+e.target.value)}
        />
      </label>
      <label>
        Retrieval rate (tickets / second)
        <input
          type="number"
          min="1"
          value={retrievalRate}
          onChange={(e) => setRetrievalRate(+e.target.value)}
        />
      </label>
      <label>
        Total tickets per vendor
        <input
          type="number"
          min="1"
          value={totalTicketsPerVendor}
          onChange={(e) => setTotalTicketsPerVendor(+e.target.value)}
        />
      </label>
      <label>
        Max pool capacity
        <input
          type="number"
          min="1"
          value={maxCapacity}
          onChange={(e) => setMaxCapacity(+e.target.value)}
        />
      </label>

      <div style={{ marginTop: 8 }}>
        <button type="submit" disabled={loading}>
          {loading ? "Starting…" : "Start Simulation"}
        </button>
      </div>
      {error && <div className="error">{error}</div>}
    </form>
  );
}
