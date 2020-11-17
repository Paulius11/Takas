import React from "react";
import "./HomeInfoItem.css";
import ChevronRightIcon from "@material-ui/icons/ChevronRight";
import { IconButton } from "@material-ui/core";

function HomeInfoItem({ Icon, title, description }) {
  return (
    <div className="homeInfoItem">
      {Icon && <Icon />}
      <h2>{title}</h2>
      <p>{description}</p>
      <div className="homeInfoItem__more" title="more">
        <IconButton>
          <ChevronRightIcon />
        </IconButton>
      </div>
    </div>
  );
}

export default HomeInfoItem;
