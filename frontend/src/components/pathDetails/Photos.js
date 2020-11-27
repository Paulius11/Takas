import React from "react";
import { useParams } from "react-router-dom";

function Photos() {
  const { id } = useParams();
  return (
    <div>
      <h1>Photos + {id}</h1>
    </div>
  );
}

export default Photos;
