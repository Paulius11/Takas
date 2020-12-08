import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./SignUp.css";
import VisibilityOffIcon from "@material-ui/icons/VisibilityOff";
import VisibilityIcon from "@material-ui/icons/Visibility";

function SignIn() {
  const [show, setShow] = useState(false);
  const showHidePassword = () => {
    setShow(!show);
  };
  return (
    <div className="signUp">
      <div className="signUp__container">
        <div className="signUp__containerLeft">
          <h1>Sign In</h1>
          <input type="email" placeholder="Enter you email" />
          <div>
            <input
              type={show ? "text" : "password"}
              placeholder="Enter you password"
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
          <button>Sign In</button>
          <hr />
          <p>
            Don't have an account? <Link to="/signup">Sing Up.</Link>
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
