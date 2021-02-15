import React, { useContext } from "react";
import {
  BrowserRouter as Router,
  Redirect,
  Route,
  Switch,
} from "react-router-dom";
import Home from "./pages/Home";
import Paths from "./pages/Paths";
import Header from "./components/header/Header";
import SinglePath from "./pages/SinglePath";
import Photos from "./components/pathDetails/Photos";
import SignIn from "./components/signin/SignIn";
import SignUp from "./components/signin/SignUp";
import Footer from "./components/footer/Footer";
import UserProfile from "./components/UserProfile/UserProfile";
import { AuthContext, AuthProvider } from "./context/AuthContext";
import { FetchProvider } from "./context/FetchContext";
import PathProvider from "./context/PathProvider";
import AdminPanel from "./components/adminPanel/AdminPanel";

// Allow only if user authenticated and redirect to home
// Props: exact, path
// Children: component to render
const AuthenticatedRoute = ({ children, ...props }) => {
  const authContext = useContext(AuthContext);
  return (
    <Route
      {...props}
      render={() =>
        authContext.isAuthenticated() ? <>{children}</> : <Redirect to="/" />
      }
    />
  );
};

//Routes only for Admin Role
const AdmindRoute = ({ children, ...props }) => {
  const authContext = useContext(AuthContext);
  return (
    <Route
      {...props}
      render={() =>
        authContext.isAuthenticated() && authContext.isAdmin() ? (
          <>{children}</>
        ) : (
          <Redirect to="/" />
        )
      }
    />
  );
};

const AppRoutes = () => {
  return (
    <>
      <Header />
      <Switch>
        <Route exact path="/paths">
          <Paths />
        </Route>
        <Route exact path="/paths/:id/photos">
          <Photos />
        </Route>
        <AuthenticatedRoute exact path="/user-profile">
          <UserProfile />
        </AuthenticatedRoute>
        <AdmindRoute exact path="/admin-panel">
          <AdminPanel />
        </AdmindRoute>
        <Route exact path="/signin">
          <SignIn />
        </Route>
        <Route exact path="/signup">
          <SignUp />
        </Route>
        {/*For the children part https://reactrouter.com/web/example/url-params */}
        <Route exact path="/paths/:id" children={<SinglePath />} />
        <Route exact path="/">
          <Home />
        </Route>
      </Switch>
      <Footer />
    </>
  );
};

function App() {
  return (
    <Router>
      <AuthProvider>
        <FetchProvider>
          <PathProvider>
            <AppRoutes />
          </PathProvider>
        </FetchProvider>
      </AuthProvider>
    </Router>
  );
}

export default App;
