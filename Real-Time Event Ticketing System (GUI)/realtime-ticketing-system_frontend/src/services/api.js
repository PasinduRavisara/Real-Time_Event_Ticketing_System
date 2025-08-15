// src/services/api.js
import axios from "axios";
const API_URL = import.meta.env.VITE_API_URL || "http://localhost:8080";

export const postStart = (params) =>
  axios.post(`${API_URL}/api/start`, null, { params });
export const postStop = () => axios.post(`${API_URL}/api/stop`);
export const getStatus = () => axios.get(`${API_URL}/api/status`);
export const getLogs = (max = 200) =>
  axios.get(`${API_URL}/api/logs`, { params: { max } });
