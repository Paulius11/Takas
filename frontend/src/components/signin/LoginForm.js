import React, { useState } from "react";
import { Formik, Form } from "formik";
import TextField from "./TextField";
import { Link } from "react-router-dom";
import * as Yup from "yup";
import "./FormStyle.css";
import VisibilityOffIcon from "@material-ui/icons/VisibilityOff";
import VisibilityIcon from "@material-ui/icons/Visibility";
import { login } from "../service/authService";

function LoginForm() {
  const [show, setShow] = useState(false);

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
    <Formik
      initialValues={{
        username: "",
        password: "",
      }}
      onSubmit={(values) => login(values)}
      validationSchema={validate}
    >
      {(formik) => (
        <div className="registration">
          <Form>
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
              Don't have an account? <Link to="/signup">Sign Up</Link>
            </p>
          </Form>
        </div>
      )}
    </Formik>
  );
}

export default LoginForm;
