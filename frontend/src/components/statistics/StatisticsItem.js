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
          {/* Count up change the number from 0 to the number you want */}
          <CountUp
            start={focus ? 0 : null}
            end={number}
            duration={4}
            redraw={true}
          >
            {({ countUpRef }) => (
              //  When user reach this part of the page the CountUp starts
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
