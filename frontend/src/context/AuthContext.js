import React, { createContext, useState } from "react";
import { useHistory } from "react-router-dom";
import jwt_decode from "jwt-decode";
import Cookies from "js-cookie";

const AuthContext = createContext();
const { Provider } = AuthContext;

const AuthProvider = ({ children }) => {
  const history = useHistory();

  const jwt = Cookies.get("token");
  const user = localStorage.getItem("user");

  const [authState, setAuthState] = useState({
    jwt,
    user: user ? JSON.parse(user) : {},
  });

  const setAuthInfo = ({ jwt, user }) => {
    Cookies.set("token", jwt);
    localStorage.setItem("user", JSON.stringify(user));

    setAuthState({
      jwt,
      user,
    });
  };

  const logout = () => {
    Cookies.remove("token");
    localStorage.removeItem("user");

    setAuthState({});
    history.push("/signin");
  };

  const isAuthenticated = () => {
    try {
      if (authState.jwt !== undefined) {
        const decoded = jwt_decode(authState.jwt);
        if (!authState.jwt && Date.now() >= decoded.exp * 1000) {
          return false;
        }
        return true;
      }
    } catch (error) {
      console.log(error);
    }
  };

  const isAdmin = () => {
    return authState.user.roles === "ROLE_ADMIN";
  };

  return (
    <Provider
      value={{
        authState,
        setAuthState: (authInfo) => setAuthInfo(authInfo),
        logout,
        isAuthenticated,
        isAdmin,
      }}
    >
      {children}
    </Provider>
  );
};

export { AuthContext, AuthProvider };
