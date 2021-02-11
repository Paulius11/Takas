import React from "react";
import "./SignUp.css";

import LoginForm from "./LoginForm";

function SignIn() {
  return (
    <div className="signUp">
      <div className="signUp__container">
        <div className="signUp__containerLeft">
          <h1>Login</h1>
          <LoginForm />
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
