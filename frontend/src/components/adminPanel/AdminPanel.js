import React from "react";
import AdminSidebar from "./AdminSidebar";
import { Link, Route } from "react-router-dom";
import UsersList from "../adminComponets/users/UsersList";
import PathsList from "../adminComponets/paths/PathsList";

// Example components
const AdminHome = () => {
  return (
    <div style={{ flex: 0.8 }}>
      <h1>Admin Home</h1>
      <Link to="/admin-panel/data/paths">Paths</Link>
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

function AdminPanel() {
  return (
    <div
      style={{
        display: "flex",
      }}
    >
      <div style={{ flex: "0.2" }}>
        <AdminSidebar />
      </div>
      <div style={{ flex: "0.8", background: "#efefef" }}>
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
          <UsersList />
        </Route>
        <Route exact path="/admin-panel/data/paths">
          <PathsList />
        </Route>
      </div>
    </div>
  );
}

export default AdminPanel;
