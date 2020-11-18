import React from "react";
import Header from "../components/header/Header";
import Jumbotron from "../components/jumbotron/Jumbotron";
import HomeInfo from "../components/homeinfo/HomeInfo";
import Featured from "../components/featured/Featured";
import Footer from "../components/footer/Footer";
import Statistics from "../components/statistics/Statistics";

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
