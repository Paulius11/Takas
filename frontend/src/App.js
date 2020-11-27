import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Home from "./pages/Home";
import Paths from "./pages/Paths";
import Header from "./components/header/Header";
import SinglePath from "./pages/SinglePath";
import Photos from "./components/pathDetails/Photos";

function App() {
  return (
    <Router>
      <Header />
      <Switch>
        <Route exact path="/paths">
          <Paths />
        </Route>
        <Route exact path="/paths/:id/photos">
          <Photos />
        </Route>
        <Route exact path="/paths/:id" children={<SinglePath />} />
        <Route exact path="/">
          <Home />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
