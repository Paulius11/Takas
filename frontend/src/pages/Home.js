import React from "react";
import Header from "../components/header/Header";
import Jumbotron from "../components/jumbotron/Jumbotron";
import HomeInfo from "../components/homeinfo/HomeInfo";
import Featured from "../components/featured/Featured";

function Home() {
  return (
    <div>
      <Header />
      <Jumbotron />
      <Featured />
      <HomeInfo />
    </div>
  );
}

export default Home;
