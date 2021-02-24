import React from "react";
import Jumbotron from "../components/jumbotron/Jumbotron";
import HomeInfo from "../components/homeinfo/HomeInfo";
import Featured from "../components/featured/Featured";
import Statistics from "../components/statistics/Statistics";
import Header from "../components/header/Header";
import Footer from "../components/footer/Footer";

function Home() {
  return (
    <div>
      <Header />
      <Jumbotron />
      <Featured />
      <HomeInfo />
      <Statistics />
      <Footer />
    </div>
  );
}

export default Home;
