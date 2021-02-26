import React from "react";
import AdminSidebar from "./AdminSidebar";
import { Route } from "react-router-dom";

// Example components
const AdminHome = () => {
  return (
    <div style={{ flex: 0.8 }}>
      <h1>Admin Home</h1>
    </div>
  );
};

const DashboardStatistics = () => {
  return (
    <div style={{ flex: 0.8 }}>
      <h1>Statistics</h1>
    </div>
  );
};

const DashboardSettings = () => {
  return (
    <div style={{ flex: 0.8 }}>
      <h1>Settings</h1>
    </div>
  );
};

const DataUsers = () => {
  return (
    <div style={{ flex: 0.8 }}>
      <h1>Users</h1>
    </div>
  );
};

const DataPaths = () => {
  return (
    <div style={{ flex: 0.8 }}>
      <h1>Paths</h1>
    </div>
  );
};

function AdminPanel() {
  return (
    <div
      style={{
        display: "flex",
      }}
    >
      <AdminSidebar />
      <Route path="/admin-panel/admin-home">
        <AdminHome />
      </Route>
      <Route path="/admin-panel/dashboard/statistics">
        <DashboardStatistics />
      </Route>
      <Route path="/admin-panel/dashboard/settings">
        <DashboardSettings />
      </Route>
      <Route path="/admin-panel/data/users">
        <DataUsers />
      </Route>
      <Route path="/admin-panel/data/paths">
        <DataPaths />
      </Route>
    </div>
  );
}

export default AdminPanel;
