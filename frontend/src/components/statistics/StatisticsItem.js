import React, { useState } from "react";
import "./StatisticsItem.css";
import CountUp from "react-countup";
import VisibilitySensor from "react-visibility-sensor";

function StatisticsItem({ Icon, number, text }) {
  const [focus, setFocus] = useState(false);
  return (
    <div className="statisticsItem">
      <div className="statisticsItem__icon">{Icon && <Icon />}</div>
      <div className="statisticsItem__text">
        <h1>
          <CountUp
            start={focus ? 0 : null}
            end={number}
            duration={4}
            redraw={true}
          >
            {({ countUpRef }) => (
              <VisibilitySensor
                onChange={(isVisible) => {
                  if (isVisible) {
                    setFocus(true);
                  }
                }}
              >
                <span ref={countUpRef} />
              </VisibilitySensor>
            )}
          </CountUp>
        </h1>
        <p>{text}</p>
      </div>
    </div>
  );
}

export default StatisticsItem;
