import React from "react";
import Modal from "@material-ui/core/Modal";
import Backdrop from "@material-ui/core/Backdrop";
import Fade from "@material-ui/core/Fade";
import "./DeleteModal.css";

export default function DeleteModal({ open, handleClose, id, handleDelete }) {
  return (
    <div>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        className="delete__modal"
        open={open}
        onClose={handleClose}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 500,
        }}
      >
        <Fade in={open}>
          <div className="delete__content">
            <h2 id="transition-modal-title">Do you want to delete "{id}"?</h2>
            <div id="transition-modal-description">
              <button onClick={handleClose}>Cancel</button>
              <button onClick={() => handleDelete(id)}>Delete</button>
            </div>
          </div>
        </Fade>
      </Modal>
    </div>
  );
}
