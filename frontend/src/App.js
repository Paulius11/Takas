import React, { useContext, lazy } from "react";
import {
  BrowserRouter as Router,
  Redirect,
  Route,
  Switch,
} from "react-router-dom";
import Home from "./pages/Home";
import Paths from "./pages/Paths";
import SinglePath from "./pages/SinglePath";
import Photos from "./components/pathDetails/Photos";
import { AuthContext, AuthProvider } from "./context/AuthContext";
import { FetchProvider } from "./context/FetchContext";
import PathProvider from "./context/PathProvider";
import { Suspense } from "react";
import * as ROUTES from "./utils/routes";
import Login from "./pages/Login";
import SignUpPage from "./pages/SignUpPage";

// For spliting app in different bundles, so we can load an appropriate bundle on appropriate time
const AdminPage = lazy(() => import("./pages/AdminPage"));
const UserProfilePage = lazy(() => import("./pages/UserProfilePage"));

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
      {/* Suspense for lazy import to work */}
      <Suspense fallback={<div>Loading...</div>}>
        <Switch>
          <Route exact path={ROUTES.PATHS}>
            <Paths />
          </Route>
          <Route exact path={ROUTES.PATHS_PHOTOS}>
            <Photos />
          </Route>
          <AuthenticatedRoute exact path={ROUTES.USER_PROFILE}>
            <UserProfilePage />
          </AuthenticatedRoute>
          <AdmindRoute path={ROUTES.ADMIN_PANEL}>
            <AdminPage />
          </AdmindRoute>
          <Route exact path={ROUTES.SIGN_IN}>
            <Login />
          </Route>
          <Route exact path={ROUTES.SIGN_UP}>
            <SignUpPage />
          </Route>
          {/*For the children part https://reactrouter.com/web/example/url-params */}
          <Route exact path={ROUTES.SINGLE_PATH} children={<SinglePath />} />
          <Route exact path={ROUTES.HOME}>
            <Home />
          </Route>
        </Switch>
      </Suspense>
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
