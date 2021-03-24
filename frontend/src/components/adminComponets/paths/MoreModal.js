import React from "react";
import "./MoreModal.css";
import CloseIcon from "@material-ui/icons/Close";

function MoreModal({ isModalOpen, closeModal, passToModal }) {
  const {
    title,
    image,
    description,
    featured,
    length,
    difficulty,
    region,
    published,
    rating,
  } = passToModal;

  return (
    <div
      className={`${
        isModalOpen ? "modal-overlay show-modal" : "modal-overlay"
      }`}
    >
      <div className="modal-container">
        <h1>More info:</h1>
        <div className="more__info">
          <h4>Title:</h4>
          <p>{title}</p>
        </div>
        <div className="more__info">
          <h4>Length:</h4>
          <p>{length}</p>
        </div>
        <div className="more__info">
          <h4>Difficulty:</h4>
          <p>{difficulty}</p>
        </div>
        <div className="more__info">
          <h4>Rating:</h4>
          <p>{rating}</p>
        </div>
        <div className="more__info">
          <h4>Region:</h4>
          <p>{region}</p>
        </div>
        <div className="more__info">
          <h4>Featured:</h4>
          <p>{featured ? `Yes` : `No`}</p>
        </div>
        <div className="more__info">
          <h4>Published:</h4>
          <p>{published ? `Yes` : `No`}</p>
        </div>
        <div className="more__info">
          <h4>Description:</h4>
          <p>{description}</p>
        </div>
        <div className="more__info">
          <img src={image} alt={title} />
        </div>
        <button className="close-modal-btn" onClick={closeModal}>
          <CloseIcon />
        </button>
      </div>
    </div>
  );
}

export default MoreModal;
