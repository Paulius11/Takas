import React from "react";
import "./Featured.css";
import FeaturedItem from "./FeaturedItem";
import pathData from "../../data/pathData.json";

function Featured() {
  return (
    <div className="featured">
      {pathData.map((data) =>
        data.featured === true ? (
          <FeaturedItem
            key={data.id}
            rating={data.rating}
            title={data.title}
            image={data.image}
            description={data.description}
          />
        ) : null
      )}
    </div>
  );
}

export default Featured;
