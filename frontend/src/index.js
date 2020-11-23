import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import PathProvider from "./context/PathProvider";

ReactDOM.render(
  <React.StrictMode>
    <PathProvider>
      <App />
    </PathProvider>
  </React.StrictMode>,
  document.getElementById("root")
);
