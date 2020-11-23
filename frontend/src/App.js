import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Home from "./pages/Home";
import Paths from "./pages/Paths";

function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/paths">
          <Paths />
        </Route>
        <Route exact path="/">
          <Home />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
