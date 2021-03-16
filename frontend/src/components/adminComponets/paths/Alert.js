import React from "react";
import "./Alert.css";
import ErrorOutlineIcon from "@material-ui/icons/ErrorOutline";
import CheckCircleOutlineIcon from "@material-ui/icons/CheckCircleOutline";
import Snackbar from "@material-ui/core/Snackbar";
import IconButton from "@material-ui/core/IconButton";
import CloseIcon from "@material-ui/icons/Close";

const Alert = ({ show, type, msg, removeAlert }) => {
  const handleClose = () => {
    removeAlert();
  };

  return (
    <div>
      <Snackbar
        anchorOrigin={{
          vertical: "top",
          horizontal: "right",
        }}
        ContentProps={{
          className: `alert alert-${type}`,
        }}
        open={show}
        onClose={handleClose}
        autoHideDuration={3000}
        message={
          <div className="alert">
            {type === "success" ? (
              <CheckCircleOutlineIcon style={{ color: "#5CB65F" }} />
            ) : (
              <ErrorOutlineIcon style={{ color: "#F45448" }} />
            )}
            {msg}
          </div>
        }
        action={
          <React.Fragment>
            <IconButton
              size="small"
              aria-label="close"
              color="inherit"
              onClick={handleClose}
            >
              <CloseIcon fontSize="small" />
            </IconButton>
          </React.Fragment>
        }
      />
    </div>
  );
};

export default Alert;
