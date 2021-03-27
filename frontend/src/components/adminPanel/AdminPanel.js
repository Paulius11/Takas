import React, { useState } from "react";
import AdminSidebar from "./AdminSidebar";
import { Route } from "react-router-dom";
import "./AdminPanel.css";
import UsersList from "../adminComponets/users/UsersList";
import PathsList from "../adminComponets/paths/PathsList";
import AddPath from "../adminComponets/paths/AddPath";
import AdminHome from "../adminComponets/adminHome/AdminHome";
import AdminHeader from "./AdminHeader";
import Page from "./Page";

// Example components
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
  const [isSidebarOpen, setIsSidebarOpen] = useState(true);

  const openSidebar = () => {
    setIsSidebarOpen(true);
  };
  const closeSidebar = () => {
    setIsSidebarOpen(false);
  };

  return (
    <div
      style={{
        display: "flex",
      }}
    >
      <div className={`${isSidebarOpen ? "show-sidebar" : "hide-sidebar"}`}>
        <AdminSidebar />
      </div>
      <div className={`${isSidebarOpen ? "sidebar-open" : "full-size"}`}>
        <AdminHeader
          isSidebarOpen={isSidebarOpen}
          openSidebar={openSidebar}
          closeSidebar={closeSidebar}
        />
        <Route
          path="/admin-panel/admin-home"
          render={(props) => (
            <Page title="Admin Home">
              <AdminHome />
            </Page>
          )}
        />
        <Route
          path="/admin-panel/dashboard/statistics"
          render={(props) => (
            <Page title="Dashboard Statistics">
              <DashboardStatistics />
            </Page>
          )}
        />
        <Route
          path="/admin-panel/dashboard/settings"
          render={(props) => (
            <Page title="Dashboard Settings">
              <DashboardSettings />
            </Page>
          )}
        />
        <Route
          path="/admin-panel/data/users"
          render={(props) => (
            <Page title="Users List">
              <UsersList />
            </Page>
          )}
        />
        <Route
          exact
          path="/admin-panel/data/paths"
          render={(props) => (
            <Page title="Paths List">
              <PathsList />
            </Page>
          )}
        />
        <Route
          path="/admin-panel/data/paths/edit/:id"
          render={(props) => (
            <Page title="Edit Path">
              <AddPath />
            </Page>
          )}
        />
        <Route
          exact
          path="/admin-panel/data/paths/add"
          render={(props) => (
            <Page title="Add Path">
              <AddPath />
            </Page>
          )}
        />
      </div>
    </div>
  );
}

export default AdminPanel;
