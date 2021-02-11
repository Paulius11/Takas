import React from "react";
import "./SignUp.css";
import RegistrationForm from "./RegistrationForm";

function SignUp() {
  return (
    <div className="signUp">
      <div className="signUp__container">
        <div className="signUp__containerLeft">
          <h1>Sign up</h1>
          <RegistrationForm />
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
