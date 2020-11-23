import React from "react";
import "./PathCard.css";
import { Link } from "react-router-dom";
import FavoriteBorderRoundedIcon from "@material-ui/icons/FavoriteBorderRounded";
import ShareRoundedIcon from "@material-ui/icons/ShareRounded";
import FavoriteRoundedIcon from "@material-ui/icons/FavoriteRounded";
import BookmarkRoundedIcon from "@material-ui/icons/BookmarkRounded";

function PathCard({
  title,
  image,
  rating,
  description,
  lenght,
  region,
  difficulty,
}) {
  return (
    <div className="pathCard">
      <div className="pathCard__header">
        <div>
          <Link to="/">{title}</Link>
          <div className="pathCard__rating">
            {Array(rating)
              .fill()
              .map((_) => (
                <span>&#9733;</span>
              ))}
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
            {region}, {lenght} km
          </p>
          <span>{difficulty}</span>
          <div className="pathCard__InfoIcons">
            <FavoriteRoundedIcon /> 14
            <BookmarkRoundedIcon /> 150
          </div>
          <p>{description}</p>
        </div>
      </div>
    </div>
  );
}

export default PathCard;
