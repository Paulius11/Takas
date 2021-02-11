import React, { useState } from "react";
import { Formik, Form } from "formik";
import TextField from "./TextField";
import { Link } from "react-router-dom";
import * as Yup from "yup";
import "./FormStyle.css";
import VisibilityOffIcon from "@material-ui/icons/VisibilityOff";
import VisibilityIcon from "@material-ui/icons/Visibility";

function RegistrationForm() {
  const [show, setShow] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  const showHidePassword = () => {
    setShow(!show);
  };
  const showHidePasswordConfirm = () => {
    setShowConfirmPassword(!showConfirmPassword);
  };

  //Field validation with yup
  const validate = Yup.object({
    username: Yup.string()
      .min(2, "The name must be 2 or more characters")
      .max(15, "The name cant be more than 15 characters")
      .required("First name is required"),
    email: Yup.string().email("Email is invalid").required("Email is required"),
    password: Yup.string()
      .min(8, "Password needs to be 8 or more characters")
      .required("Password is required")
      .matches(
        /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{8,}$/,
        "Must contain Uppercase, Lowercase and Number"
      ),
    confirmPassword: Yup.string()
      .oneOf([Yup.ref("password"), null], "Passwords must match")
      .required("Confirm password is required"),
  });
  return (
    <Formik
      initialValues={{
        username: "",
        email: "",
        password: "",
        confirmPassword: "",
      }}
      onSubmit={() => console.log("Success")}
      validationSchema={validate}
    >
      {(formik) => (
        <div className="registration">
          <Form>
            <TextField placeholder="username" name="username" type="text" />
            <TextField placeholder="email" name="email" type="email" />

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
              placeholder="confirmPassword"
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
              Have an account? <Link to="/signin">Sign In</Link>
            </p>
          </Form>
        </div>
      )}
    </Formik>
  );
}

export default RegistrationForm;
