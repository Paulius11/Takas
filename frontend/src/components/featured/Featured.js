import React from "react";
import "./Featured.css";
import FeaturedItem from "./FeaturedItem";

function Featured() {
  return (
    <div className="featured">
      <FeaturedItem
        rating={4}
        title="The Bear Path"
        image="https://cdn-assets.alltrails.com/uploads/photo/image/22928103/large_2af738da41f0100ddc3dc12110e89c2d.jpg"
        description="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet est molestie, ultrices velit non, faucibus mauris. Maecenas vel leo in dolor tempor aliquet. Cras ut egestas eros. Morbi tincidunt malesuada euismod."
      />
      <FeaturedItem
        rating={5}
        title="The Greatest Road"
        image="https://www.pittsburghmagazine.com/content/uploads/2020/03/cb-cook-forest-trail1.jpg"
        description="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet est molestie, ultrices velit non, faucibus mauris. Maecenas vel leo in dolor tempor aliquet. Cras ut egestas eros. Morbi tincidunt malesuada euismod."
      />
      <FeaturedItem
        rating={5}
        title="The Dark Mist"
        image="https://www.hikespeak.com/img/Oregon/Forest_Park/Wildwood_Trail_Forest_Park_Portland_hike_5405.jpg"
        description="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet est molestie, ultrices velit non, faucibus mauris. Maecenas vel leo in dolor tempor aliquet. Cras ut egestas eros. Morbi tincidunt malesuada euismod."
      />
    </div>
  );
}

export default Featured;
