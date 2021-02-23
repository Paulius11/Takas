import React from "react";
import "./Message.css";

function SuccessMessage({ successMessage }) {
  return (
    <div className="success__container">
      <h4>{successMessage}</h4>
    </div>
  );
}

export default SuccessMessage;
