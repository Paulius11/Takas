import React, { createContext, useState } from "react";
import { useHistory } from "react-router-dom";
import jwt_decode from "jwt-decode";

const AuthContext = createContext();
const { Provider } = AuthContext;

const AuthProvider = ({ children }) => {
  const history = useHistory();

  const jwt = localStorage.getItem("token");
  const user = localStorage.getItem("user");

  const [authState, setAuthState] = useState({
    jwt,
    user: user ? JSON.parse(user) : {},
  });

  const setAuthInfo = ({ jwt, user }) => {
    localStorage.setItem("token", jwt);
    localStorage.setItem("user", JSON.stringify(user));

    setAuthState({
      jwt,
      user,
    });
  };

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");

    setAuthState({});
    history.push("/signin");
  };

  const isAuthenticated = () => {
    // const decoded_token = jwt_decode(jwt);
    if (!authState.jwt) {
      return false;
    }
    return true;
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
