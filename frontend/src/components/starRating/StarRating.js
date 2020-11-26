import React, { useState } from "react";
import "./StarRating.css";
import StarRoundedIcon from "@material-ui/icons/StarRounded";

function StarRating({ rate }) {
  const [rating, setRating] = useState(rate);
  const [hover, setHover] = useState(null);

  return (
    <div>
      {[...Array(5)].map((_, i) => {
        const ratingValue = i + 1;
        return (
          <label key={i} className="starRating__radio">
            <input
              type="radio"
              name="rating"
              value={ratingValue}
              onClick={() => setRating(ratingValue)}
            />
            <StarRoundedIcon
              style={
                ratingValue <= (hover || rating)
                  ? { color: "#ffc107" }
                  : { color: "#e4e5e9" }
              }
              className="startRating__star"
              onMouseEnter={() => setHover(ratingValue)}
              onMouseLeave={() => setHover(null)}
            />
          </label>
        );
      })}
    </div>
  );
}

export default StarRating;
