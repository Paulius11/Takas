import React from "react";
import "./Statistics.css";
import PeopleAltOutlinedIcon from "@material-ui/icons/PeopleAltOutlined";
import CameraAltOutlinedIcon from "@material-ui/icons/CameraAltOutlined";
import EmojiNatureOutlinedIcon from "@material-ui/icons/EmojiNatureOutlined";
import WallpaperOutlinedIcon from "@material-ui/icons/WallpaperOutlined";
import StatisticsItem from "./StatisticsItem";

function Statistics() {
  return (
    <div className="statistics">
      <div className="statistics__container">
        <StatisticsItem
          Icon={EmojiNatureOutlinedIcon}
          number={53}
          text="Paths"
        />
        <StatisticsItem
          Icon={WallpaperOutlinedIcon}
          number={1233}
          text="Walked paths"
        />
        <StatisticsItem
          Icon={PeopleAltOutlinedIcon}
          number={450}
          text="Happy hikers"
        />
        <StatisticsItem
          Icon={CameraAltOutlinedIcon}
          number={8433}
          text="Pictures taken"
        />
      </div>
    </div>
  );
}

export default Statistics;
