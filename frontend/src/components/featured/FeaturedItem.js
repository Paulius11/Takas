import React from "react";
import "./FeaturedItem.css";
import MoreVertRoundedIcon from "@material-ui/icons/MoreVertRounded";
import FavoriteBorderRoundedIcon from "@material-ui/icons/FavoriteBorderRounded";
import ShareRoundedIcon from "@material-ui/icons/ShareRounded";
import StarRating from "../starRating/StarRating";
import { Link } from "react-router-dom";

function FeaturedItem({ image, rating, title, description, id }) {
  return (
    <div className="featuredItem">
      <div className="featuredItem__header">
        <div className="featuredItem__headerLeft">
          <Link to={`/paths/${id}`}>{title}</Link>
          <div className="featuredItem__rating">
            <StarRating rate={rating} />
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
