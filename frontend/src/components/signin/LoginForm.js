import React, { useContext, useState } from "react";
import { Formik, Form } from "formik";
import TextField from "./TextField";
import { Link, Redirect } from "react-router-dom";
import * as Yup from "yup";
import "./FormStyle.css";
import VisibilityOffIcon from "@material-ui/icons/VisibilityOff";
import VisibilityIcon from "@material-ui/icons/Visibility";
import { publicFetch } from "./../../utils/fetch";
import SuccessMessage from "./SuccessMessage";
import { AuthContext } from "../../context/AuthContext";
import ErrorMessage from "./ErrorMessage";
import * as ROUTES from "./../../utils/routes";

function LoginForm() {
  const authContext = useContext(AuthContext);
  const [show, setShow] = useState(false);
  const [loginSuccess, setLoginSuccess] = useState();
  const [loginError, setLoginError] = useState();
  const [redirectOnLogin, setRedirectOnLogin] = useState(false);
  const [loginLoading, setLoginLoading] = useState(false);

  //Function for user login
  const submitCredentials = async (credentials) => {
    try {
      setLoginLoading(true);
      const { data } = await publicFetch.post(`login`, credentials);
      authContext.setAuthState(data);
      setLoginSuccess(data.message);
      setLoginError(null);
      setTimeout(() => {
        setRedirectOnLogin(true);
      }, 1000);
      console.log(data);
    } catch (error) {
      setLoginLoading(false);
      const { data } = error.response;
      setLoginError(data.message);
      setLoginSuccess(null);
    }
  };

  const showHidePassword = () => {
    setShow(!show);
  };

  //Field validation with yup
  const validate = Yup.object({
    username: Yup.string()
      .min(2, "The name must be 2 or more characters")
      .max(15, "The name cant be more than 15 characters")
      .required("Username is required"),
    password: Yup.string()
      .min(8, "Password needs to be 8 or more characters")
      .required("Password is required"),
  });

  return (
    <>
      {redirectOnLogin && <Redirect to="/user-profile" />}
      <Formik
        initialValues={{
          username: "",
          password: "",
        }}
        onSubmit={(values) => submitCredentials(values)}
        validationSchema={validate}
      >
        {(formik) => (
          <div className="registration">
            <Form>
              {loginSuccess && <SuccessMessage successMessage={loginSuccess} />}
              {loginError && <ErrorMessage errorMessage={loginError} />}
              <TextField placeholder="username" name="username" type="text" />
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
              <button type="submit">Login</button>
              <p>
                Don't have an account? <Link to={ROUTES.SIGN_UP}>Sign Up</Link>
              </p>
            </Form>
          </div>
        )}
      </Formik>
    </>
  );
}

export default LoginForm;
