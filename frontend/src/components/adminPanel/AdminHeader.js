import React from "react";
import MenuIcon from "@material-ui/icons/Menu";
import CloseIcon from "@material-ui/icons/Close";

function AdminHeader({ openSidebar, isSidebarOpen, closeSidebar }) {
  return (
    <div className="admin__header">
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
      <h1>Header</h1>
    </div>
  );
}

export default AdminHeader;
