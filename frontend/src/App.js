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
import Error from "./pages/Error";
import Page from "./components/adminPanel/Page";
import Footer from "./components/footer/Footer";
import Header from "./components/header/Header";
import { AdminPathProvider } from "./context/AdminPathContext";

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
  const authContext = useContext(AuthContext);
  return (
    <>
      {/* Suspense for lazy import to work */}
      <Suspense fallback={<div>Loading...</div>}>
        <Switch>
          <Route
            exact
            path={ROUTES.PATHS}
            render={(props) => (
              <Page title="Paths">
                <Paths />
              </Page>
            )}
          />
          <Route exact path={ROUTES.PATHS_PHOTOS}>
            <Photos />
          </Route>
          <Route
            path={ROUTES.USER_PROFILE}
            render={(props) =>
              authContext.isAuthenticated() ? (
                <>
                  <Page title={`${props.match.params.username} Profile`}>
                    <UserProfilePage />
                  </Page>
                </>
              ) : (
                <Redirect to="/" />
              )
            }
          ></Route>
          <AdmindRoute path={ROUTES.ADMIN_PANEL}>
            <AdminPage />
          </AdmindRoute>
          <Route
            exact
            path={ROUTES.SIGN_IN}
            render={(props) => (
              <Page title="Login">
                <Login />
              </Page>
            )}
          ></Route>
          <Route
            exact
            path={ROUTES.SIGN_UP}
            render={(props) => (
              <Page title="Sign Up">
                <SignUpPage />
              </Page>
            )}
          ></Route>
          {/*For the children part https://reactrouter.com/web/example/url-params */}
          <Route
            exact
            path={ROUTES.SINGLE_PATH}
            render={(props) => (
              <Page title={`Path ${props.match.params.id}`}>
                <SinglePath />
              </Page>
            )}
          />
          <Route
            exact
            path={ROUTES.HOME}
            render={(props) => (
              <Page title="Home">
                <Home />
              </Page>
            )}
          ></Route>
          <Route
            exact
            path="/featured"
            render={(props) => (
              <Page title="Featured Paths">
                <Featured />
              </Page>
            )}
          ></Route>
          <Route
            exact
            path="/contacts"
            render={(props) => (
              <Page title="Contact Us">
                <ContactUs />
              </Page>
            )}
          ></Route>
          {/*For error - Page not found */}
          <Route path="*">
            <Error />
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
          <AdminPathProvider>
            <PathProvider>
              <AppRoutes />
            </PathProvider>
          </AdminPathProvider>
        </FetchProvider>
      </AuthProvider>
    </Router>
  );
}

// Temp components
const Featured = () => {
  return (
    <>
      <Header />
      <h1 style={{ height: "300px" }}>Featured</h1>
      <Footer />
    </>
  );
};

const ContactUs = () => {
  return (
    <>
      <Header />
      <h1 style={{ height: "300px" }}>Contact Us</h1>
      <Footer />
    </>
  );
};

export default App;
