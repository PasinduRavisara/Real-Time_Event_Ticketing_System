// src/components/LogViewer.jsx
import React, { useRef, useEffect } from "react";

export default function LogViewer({ logs }) {
  const listRef = useRef(null);

  useEffect(() => {
    // auto-scroll to bottom when logs update
    if (listRef.current) {
      listRef.current.scrollTop = listRef.current.scrollHeight;
    }
  }, [logs]);

  return (
    <div className="card log-viewer">
      <h3>Logs</h3>
      <div ref={listRef} style={{ maxHeight: 300, overflow: "auto" }}>
        {logs.length === 0 ? (
          <div className="muted">No logs yet</div>
        ) : (
          logs.map((l, idx) => (
            <div key={idx} className="log-row">
              <div className="log-time">
                {new Date(l.timestamp).toLocaleTimeString()}
              </div>
              <div className="log-message">
                [{l.level}] {l.message}
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
}
