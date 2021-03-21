import React, { useState } from "react";
import AdminSidebar from "./AdminSidebar";
import { Route } from "react-router-dom";
import "./AdminPanel.css";
import UsersList from "../adminComponets/users/UsersList";
import PathsList from "../adminComponets/paths/PathsList";
import AddPath from "../adminComponets/paths/AddPath";
import AdminHome from "../adminComponets/adminHome/AdminHome";
import MenuIcon from "@material-ui/icons/Menu";
import CloseIcon from "@material-ui/icons/Close";

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
        <button
          onClick={openSidebar}
          className={isSidebarOpen ? "hide-menu" : "show-menu"}
        >
          <MenuIcon />
        </button>
        <button
          onClick={closeSidebar}
          className={isSidebarOpen ? "show-close" : "hide-close"}
        >
          <CloseIcon />
        </button>
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
        <Route exact path="/admin-panel/data/paths/edit/:id">
          <AddPath />
        </Route>
        <Route exact path="/admin-panel/data/paths/add">
          <AddPath />
        </Route>
      </div>
    </div>
  );
}

export default AdminPanel;
