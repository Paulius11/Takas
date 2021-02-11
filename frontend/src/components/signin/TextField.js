import React from "react";
import { useField, ErrorMessage } from "formik";
import "./FormStyle.css";

function TextField({ placeholder, ...props }) {
  const [field, meta] = useField(props);
  return (
    <div className="inputBox">
      <input
        className={`textField__input ${
          meta.touched && meta.error && "isInvalid"
        }`}
        autoComplete="off"
        {...field}
        {...props}
        placeholder={placeholder}
      />
      <ErrorMessage component="div" name={field.name} className="error" />
    </div>
  );
}

export default TextField;
