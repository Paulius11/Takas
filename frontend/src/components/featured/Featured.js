import React, { useContext } from "react";
import "./Featured.css";
import FeaturedItem from "./FeaturedItem";
import { PathContext } from "../../context/PathProvider";

function Featured() {
  const { paths } = useContext(PathContext);
  return (
    <div className="featured">
      {paths.map((data) =>
        data.featured === true ? <FeaturedItem key={data.id} {...data} /> : null
      )}
    </div>
  );
}

export default Featured;
