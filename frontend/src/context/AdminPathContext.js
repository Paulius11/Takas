import axios from "axios";
import React, { useState, useContext, useEffect } from "react";
import { BASE_URL } from "../utils/URL";
import Cookies from "js-cookie";

const AdminPathContext = React.createContext();

axios.interceptors.request.use((config) => {
  config.headers.authorization = "Bearer " + Cookies.get("token");
  return config;
});

const AdminPathProvider = ({ children }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [open, setOpen] = useState(false);
  const [alert, setAlert] = useState({ show: false, msg: "", type: "" });

  const showAlert = (show = false, type = "", msg = "") => {
    setAlert({ show, type, msg });
  };

  const openModal = () => {
    setIsModalOpen(true);
  };
  const closeModal = () => {
    setIsModalOpen(false);
  };

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <AdminPathContext.Provider
      value={{
        handleOpen,
        openModal,
        open,
        handleClose,
        isModalOpen,
        closeModal,
        alert,
        showAlert,
      }}
    >
      {children}
    </AdminPathContext.Provider>
  );
};

export const useGlobalContext = () => {
  return useContext(AdminPathContext);
};

export { AdminPathContext, AdminPathProvider };
