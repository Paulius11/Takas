import React from "react";
import Jumbotron from "../components/jumbotron/Jumbotron";
import HomeInfo from "../components/homeinfo/HomeInfo";
import Featured from "../components/featured/Featured";
import Statistics from "../components/statistics/Statistics";

function Home() {
  return (
    <div>
      <Jumbotron />
      <Featured />
      <HomeInfo />
      <Statistics />
    </div>
  );
}

export default Home;
