import React from "react";
import { Link } from "react-router-dom";
import "./Error.css";

function Error() {
  return (
    <div className="error__page">
      <div className="error__pageContainer">
        <h1>404</h1>
        <h3>Page not Found</h3>
      </div>
      <p>
        The page you are looking for was removed, moved or simply does not
        exist.
      </p>
      <Link to="/">Return Home</Link>
    </div>
  );
}

export default Error;
