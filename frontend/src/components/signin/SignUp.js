import React, { useState } from "react";
import "./SignUp.css";
import { Link } from "react-router-dom";
import VisibilityIcon from "@material-ui/icons/Visibility";
import CheckIcon from "@material-ui/icons/Check";
import ClearIcon from "@material-ui/icons/Clear";
import VisibilityOffIcon from "@material-ui/icons/VisibilityOff";

function SignUp() {
  const [show, setShow] = useState(false);

  const valid = (item, v_icon, inv_icon) => {
    let text = document.querySelector(`#${item}`);
    text.style.opacity = "1";

    let valid_icon = document.querySelector(`#${item}  .${v_icon}`);
    valid_icon.style.opacity = "1";

    let invalid_icon = document.querySelector(`#${item}  .${inv_icon}`);
    invalid_icon.style.opacity = "0";
  };

  const invalid = (item, v_icon, inv_icon) => {
    let text = document.querySelector(`#${item}`);
    text.style.opacity = "0.5";

    let valid_icon = document.querySelector(`#${item}  .${v_icon}`);
    valid_icon.style.opacity = "0";

    let invalid_icon = document.querySelector(`#${item}  .${inv_icon}`);
    invalid_icon.style.opacity = "1";
  };

  const handlePasswordChange = (e) => {
    const password = e.target.value;

    if (password.match(/[A-Z]/) != null) {
      valid("capital", "check", "clear");
    } else {
      invalid("capital", "check", "clear");
    }
    if (password.match(/[0-9]/) != null) {
      valid("num", "check", "clear");
    } else {
      invalid("num", "check", "clear");
    }

    if (password.length > 7) {
      valid("more8", "check", "clear");
    } else {
      invalid("more8", "check", "clear");
    }
  };

  const showHidePassword = () => {
    setShow(!show);
  };

  return (
    <div className="signUp">
      <div className="signUp__container">
        <div className="signUp__containerLeft">
          <h1>Sign up</h1>
          <input type="text" placeholder="Enter username" />
          <input type="email" placeholder="Enter you email" />
          <div>
            <input
              type={show ? "text" : "password"}
              placeholder="Enter you password"
              onChange={handlePasswordChange}
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

          <div className="icons" style={{ display: "flex" }}>
            <p id="capital">
              <ClearIcon className="clear icon" />
              <CheckIcon className="check icon" />
              <span>Capital Letters</span>
            </p>
          </div>
          <div className="icons" style={{ display: "flex" }}>
            <p id="num">
              <ClearIcon className="clear icon" />
              <CheckIcon className="check icon" />
              <span> Use numbers</span>
            </p>
          </div>
          <div className="icons" style={{ display: "flex" }}>
            <p id="more8">
              <ClearIcon className="clear icon" />
              <CheckIcon className="check icon" />
              <span> More then 8 characters</span>
            </p>
          </div>
          <button>Sign up</button>
          <hr />
          <p>
            Have an account? <Link to="/signin">Sing In.</Link>
          </p>
        </div>
        <div className="signUp__or">
          <div></div>
          <p>Or</p>
          <div></div>
        </div>
        <div className="signUp__containerRight">
          <h3>Sign up with: </h3>
          <button>Google</button>
          <button>Facebook</button>
        </div>
      </div>
    </div>
  );
}

export default SignUp;
