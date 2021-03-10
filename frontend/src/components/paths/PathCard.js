import React from "react";
import "./PathCard.css";
import { Link } from "react-router-dom";
import FavoriteBorderRoundedIcon from "@material-ui/icons/FavoriteBorderRounded";
import ShareRoundedIcon from "@material-ui/icons/ShareRounded";
import FavoriteRoundedIcon from "@material-ui/icons/FavoriteRounded";
import BookmarkRoundedIcon from "@material-ui/icons/BookmarkRounded";
import StarRating from "../starRating/StarRating";

function PathCard({
  title,
  image,
  rating,
  description,
  length,
  region,
  difficulty,
  id,
}) {
  return (
    <div className="pathCard">
      <div className="pathCard__header">
        <div>
          <Link to={`/paths/${id}`}>{title}</Link>
          <div className="pathCard__rating">
            <StarRating rate={rating} />
          </div>
        </div>
        <div className="pathCard__headerIcons">
          <FavoriteBorderRoundedIcon />
          <ShareRoundedIcon />
        </div>
      </div>
      <div className="pathCard__body">
        <div className="pathCard__image">
          <Link to="/">
            <img alt="" src={image} />
          </Link>
        </div>
        <div className="pathCard__info">
          <p>
            {region}, {length} km
          </p>
          <span>{difficulty}</span>
          <div className="pathCard__InfoIcons">
            <FavoriteRoundedIcon /> 14
            <BookmarkRoundedIcon /> 150
          </div>
          {/* If descprition is longer than 120(can be changed) characters, cut the text */}
          <p>{description.substring(0, 120)}...</p>
        </div>
      </div>
    </div>
  );
}

export default PathCard;
