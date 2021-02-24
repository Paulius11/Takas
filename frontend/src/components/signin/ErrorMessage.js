import React from "react";
import "./Message.css";

function ErrorMessage({ errorMessage }) {
  return (
    <div className="error__container">
      <h4>{errorMessage}</h4>
    </div>
  );
}

export default ErrorMessage;
