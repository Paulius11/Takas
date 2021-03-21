import React, { useState } from "react";
import { Formik, Form } from "formik";
import TextField from "./TextField";
import { Link, Redirect } from "react-router-dom";
import * as Yup from "yup";
import "./FormStyle.css";
import VisibilityOffIcon from "@material-ui/icons/VisibilityOff";
import VisibilityIcon from "@material-ui/icons/Visibility";
import { publicFetch } from "./../../utils/fetch";
import SuccessMessage from "./SuccessMessage";
import ErrorMessage from "./ErrorMessage";
import * as ROUTES from "./../../utils/routes";

function RegistrationForm() {
  const [show, setShow] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const [signupSuccess, setSignupSuccess] = useState();
  const [signupError, setSignupError] = useState();
  const [redirectOnLogin, setRedirectOnLogin] = useState(false);
  const [loginLoading, setLoginLoading] = useState(false);

  //Hiding password
  const showHidePassword = () => {
    setShow(!show);
  };
  const showHidePasswordConfirm = () => {
    setShowConfirmPassword(!showConfirmPassword);
  };

  //Create user
  const submitCredentials = async (credentials) => {
    try {
      setLoginLoading(true);
      const { data } = await publicFetch.post(`register`, credentials);

      setSignupSuccess(data.message);
      setSignupError("");

      setTimeout(() => {
        setRedirectOnLogin(true);
      }, 700);
    } catch (error) {
      setLoginLoading(false);
      const { data } = error.response;
      setSignupError(data.message);
      setSignupSuccess("");
    }
  };

  //Field validation with yup
  const validate = Yup.object({
    username: Yup.string()
      .min(2, "The name must be 2 or more characters")
      .max(15, "The name cant be more than 15 characters")
      .required("First name is required")
      .matches(/^\S*$/, "No space allowed"),
    email: Yup.string().email("Email is invalid").required("Email is required"),
    password: Yup.string()
      .min(8, "Password needs to be 8 or more characters")
      .required("Password is required")
      .matches(/^\S*$/, "No space allowed")
      .matches(
        /^(?=.*[A-Za-z])(?=.*\d)(?=.*[A-Z])[A-Za-z\d@$!%*#?&]{8,}$/,
        "Must contain Uppercase, Lowercase and Number"
      ),
    confirmPassword: Yup.string()
      .oneOf([Yup.ref("password"), null], "Passwords must match")
      .required("Confirm password is required"),
  });
  return (
    <>
      {redirectOnLogin && <Redirect to="/signin" />}
      <Formik
        initialValues={{
          username: "",
          email: "",
          password: "",
          confirmPassword: "",
        }}
        onSubmit={(values) => submitCredentials(values)}
        validationSchema={validate}
      >
        {(formik) => (
          <div className="registration">
            <Form>
              {signupSuccess && (
                <SuccessMessage successMessage={signupSuccess} />
              )}
              {signupError && <ErrorMessage errorMessage={signupError} />}
              <TextField placeholder="username" name="username" type="text" />
              <TextField
                placeholder="email@example.com"
                name="email"
                type="email"
              />
              <TextField
                placeholder="password"
                name="password"
                type={show ? "text" : "password"}
              />
              {show ? (
                <VisibilityIcon
                  className="visabilitya"
                  onClick={showHidePassword}
                />
              ) : (
                <VisibilityOffIcon
                  className="visabilitya"
                  onClick={showHidePassword}
                />
              )}
              <TextField
                placeholder="confirm password"
                name="confirmPassword"
                type={showConfirmPassword ? "text" : "password"}
              />

              {showConfirmPassword ? (
                <VisibilityIcon
                  className="visabilitya"
                  onClick={showHidePasswordConfirm}
                />
              ) : (
                <VisibilityOffIcon
                  className="visabilitya"
                  onClick={showHidePasswordConfirm}
                />
              )}
              <button type="submit">Sign Up</button>
              <p>
                Have an account? <Link to={ROUTES.SIGN_IN}>Sign In</Link>
              </p>
            </Form>
          </div>
        )}
      </Formik>
    </>
  );
}

export default RegistrationForm;
