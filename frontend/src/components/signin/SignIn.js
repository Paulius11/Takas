import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./SignUp.css";
import VisibilityOffIcon from "@material-ui/icons/VisibilityOff";
import VisibilityIcon from "@material-ui/icons/Visibility";
import { getArticleById, login } from "../service/authService";
import axios from "axios";

function SignIn() {
  const [show, setShow] = useState(false);
  const showHidePassword = () => {
    setShow(!show);
  };

  const [details, setDetails] = useState({ username: "", password: "" });

  const submitHandler = (e) => {
    if (details.username !== "" && details.password !== "") {
      login(details);
    }
  };
  return (
    <div className="signUp">
      <div className="signUp__container">
        <div className="signUp__containerLeft">
          <h1>Sign In</h1>
          <input
            type="username"
            placeholder="Enter your username"
            onChange={(e) =>
              setDetails({ ...details, username: e.target.value })
            }
            value={details.username}
          />
          <div>
            <input
              type={show ? "text" : "password"}
              placeholder="Enter you password"
              onChange={(e) =>
                setDetails({ ...details, password: e.target.value })
              }
              value={details.password}
            />
            {show ? (
              <VisibilityIcon
                className="visability"
                onClick={showHidePassword}
              />
            ) : (
              <VisibilityOffIcon
                className="visability"
                onClick={showHidePassword}
              />
            )}
          </div>
          <button onClick={submitHandler}>Sign In</button>
          <hr />
          <p>
            Don't have an account? <Link to="/signup">Sign Up.</Link>
          </p>
        </div>
        <div className="signUp__or">
          <div></div>
          <p>Or</p>
          <div></div>
        </div>
        <div className="signUp__containerRight">
          <h3>Sign in with: </h3>
          <button>Google</button>
          <button>Facebook</button>
        </div>
      </div>
    </div>
  );
}

export default SignIn;
