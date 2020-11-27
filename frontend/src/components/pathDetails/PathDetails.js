import React, { useContext } from "react";
import { useParams } from "react-router-dom";
import { PathContext } from "../../context/PathProvider";
import Loading from "../loading/Loading";
import "./PathDetails.css";
import PathDetailsHeader from "../pathDetailsHeader.js/PathDetailsHeader";
import StarRating from "../starRating/StarRating";

function PathDetails() {
  const { id } = useParams();
  const { paths } = useContext(PathContext);
  const path = paths.find((item) => item.id === parseInt(id));
  if (paths.length === 0) {
    return <Loading />;
  } else {
    const {
      title,
      description,
      rating,
      image,
      region,
      difficulty,
      length,
      id,
    } = path;
    return (
      <div className="pathDetails">
        <div className="pathDetails__container">
          <PathDetailsHeader title={title} id={id} />
          <div className="pathDetails__body">
            <div className="pathDetails__bodyImage">
              <img alt={title} src={image} />
            </div>
            <div className="pathDetails__bodyInfo">
              <div className="pathDetails__bodyInfoTop">
                <div>
                  <h1>{rating}</h1>
                </div>
                <div>
                  <div className="pathDetails__rating">
                    <p>Your rating:</p>
                    <StarRating rate={rating} />
                  </div>
                  <div className="pathDetails__ratingAll">
                    <p>Rating:</p>
                    <span>
                      <strong>{rating}/5</strong> from <strong>2</strong> users
                    </span>
                  </div>
                  <div className="pathDetails__difficulty">
                    <p>Difficulty:</p>
                    <h4>{difficulty}</h4>
                  </div>
                  <div className="pathDetails__length">
                    <p>Length:</p>
                    <h4>{length} km</h4>
                  </div>
                </div>
              </div>
              <div className="pathDetails__bodyInfoText">
                <div className="pathDetails__region">
                  <h3>{region}</h3>
                </div>
                <p>{description}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default PathDetails;
