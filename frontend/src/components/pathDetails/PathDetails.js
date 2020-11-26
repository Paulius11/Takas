import React, { useContext } from "react";
import { useParams, Link } from "react-router-dom";
import { PathContext } from "../../context/PathProvider";
import Loading from "../loading/Loading";
import "./PathDetails.css";

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
          <h1>{id}</h1>
          <Link to={`/paths/${id}/photos`}>Photos</Link>
          <h1>{title}</h1>
          <h2>{rating}</h2>
          <h3>{difficulty}</h3>
          <h4>{length}</h4>
          <h5>{region}</h5>
          <p>{description}</p>
          <img alt={title} src={image} />
        </div>
      </div>
    );
  }
}

export default PathDetails;
