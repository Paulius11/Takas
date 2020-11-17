import React from "react";
import "./Jumbotron.css";

function Jumbotron() {
  return (
    <div className="jumbotron">
      <div className="jumbotron__info">
        <h1>Let's enjoy the wonders of the nature TOGETHER!</h1>
        <p>
          Lorem ipsum dolor sit amet conse ctetur adipisicing elit, sed do
          eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
          minim veniam, quis nostrud exercitation ullamco laboris nisi ut
          aliquip ex ea commodo consequat. Duis aute irure dolor in
          reprehenderit in oluptate velit esse cillum dolore.
        </p>
        <button className="jumbotron__buttonExplore">Explore</button>
        <button className="jumbotron__buttonSignIn">Sign In</button>
      </div>
      <div className="jumbotron__image">
        <img src="/images/hike.png" alt="hiking" />
      </div>
    </div>
  );
}

export default Jumbotron;
