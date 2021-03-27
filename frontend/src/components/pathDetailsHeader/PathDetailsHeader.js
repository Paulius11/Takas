import React from "react";
import { Link } from "react-router-dom";
import "./PathDetailsHeader.css";
import FavoriteBorderRoundedIcon from "@material-ui/icons/FavoriteBorderRounded";

function PathDetailsHeader({ title, id }) {
  return (
    <div className="pathDetailsHeader">
      <div className="pathDetailsHeader__title">
        <h1>{title}</h1>
        <FavoriteBorderRoundedIcon />
      </div>
      <div className="pathDetailsHeader__containerLinks">
        <Link to={`/paths/${id}`}>Details</Link>
        <Link to={`/paths/${id}/map`}>Map</Link>
        <Link to={`/paths/${id}/sightseeing`}>Sightseeing</Link>
        <Link to={`/paths/${id}/reviews`}>Reviews</Link>
        <Link to={`/paths/${id}/photos`}>Photos</Link>
        <Link to={`/paths/${id}/statistics`}>Statistics</Link>
      </div>
    </div>
  );
}

export default PathDetailsHeader;
