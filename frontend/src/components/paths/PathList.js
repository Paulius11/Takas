import React, { useContext } from "react";
import "./PathList.css";
import PathCard from "./PathCard";
import Filtered from "../filtered/Filtered";
import { PathContext } from "../../context/PathProvider";
import Loading from "../loading/Loading";

function PathList({ paths }) {
  const { loading, sorted } = useContext(PathContext);

  if (loading) {
    return <Loading />;
  } else {
    return (
      <div className="pathList">
        <div className="pathList__container">
          <div className="pathList__filters">
            <Filtered />
          </div>
          <div className="pathList__list">
            {paths.map((path) => (
              <PathCard key={path.id} {...path} />
            ))}
          </div>
        </div>
      </div>
    );
  }
}

export default PathList;
