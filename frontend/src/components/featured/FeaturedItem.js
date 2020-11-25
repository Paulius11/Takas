import React from "react";
import "./FeaturedItem.css";
import MoreVertRoundedIcon from "@material-ui/icons/MoreVertRounded";
import FavoriteBorderRoundedIcon from "@material-ui/icons/FavoriteBorderRounded";
import ShareRoundedIcon from "@material-ui/icons/ShareRounded";

function FeaturedItem({ image, rating, title, description }) {
  return (
    <div className="featuredItem">
      <div className="featuredItem__header">
        <div className="featuredItem__headerLeft">
          <h2>{title}</h2>
          <div className="featuredItem__rating">
            {Array(rating)
              .fill()
              .map((_, index) => (
                <span key={index}>&#9733;</span>
              ))}
          </div>
        </div>
      </div>
      <img src={image} alt={title} />
      <p>{description}</p>
      <div className="featuredItem__icons">
        <MoreVertRoundedIcon />
        <FavoriteBorderRoundedIcon />
        <ShareRoundedIcon />
      </div>
    </div>
  );
}

export default FeaturedItem;
