import React from "react";
import EmojiFlagsIcon from "@material-ui/icons/EmojiFlags";
import ExploreOutlinedIcon from "@material-ui/icons/ExploreOutlined";
import CameraEnhanceOutlinedIcon from "@material-ui/icons/CameraEnhanceOutlined";
import "./HomeInfo.css";
import HomeInfoItem from "./HomeInfoItem";

function HomeInfo() {
  return (
    <div className="homeInfo">
      <div className="homeInfo__item">
        <HomeInfoItem
          Icon={EmojiFlagsIcon}
          title="TAKE A HIKE"
          description="Consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitaullaboris nisi ut aliquip."
        />
      </div>
      <div className="homeInfo__item">
        <HomeInfoItem
          Icon={ExploreOutlinedIcon}
          title="HIKING DESTINATIONS"
          description="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitaullamco."
        />
      </div>
      <div className="homeInfo__item">
        <HomeInfoItem
          Icon={CameraEnhanceOutlinedIcon}
          title="HIKING ESSENTIALS"
          description="Dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitau."
        />
      </div>
    </div>
  );
}

export default HomeInfo;
