import React, { useState, useEffect, useContext } from "react";
import "./Header.css";
import { Link } from "react-router-dom";
import MenuRoundedIcon from "@material-ui/icons/MenuRounded";
import TrackChangesRoundedIcon from "@material-ui/icons/TrackChangesRounded";
import CloseRoundedIcon from "@material-ui/icons/CloseRounded";
import { AuthContext } from "../../context/AuthContext";
import AvatarDropdown from "./AvatarDropdown";
import * as ROUTES from "./../../utils/routes";

function Header() {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);

  const authContext = useContext(AuthContext);

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);
  const showButton = () => {
    if (window.innerWidth <= 960) {
      setButton(false);
    } else {
      setButton(true);
    }
  };

  useEffect(() => {
    showButton();
    return () => setClick(false);
  }, []);

  window.addEventListener("resize", showButton);

  return (
    <div className="header">
      <div className="header__container container">
        <Link
          to={ROUTES.HOME}
          className="header__logo"
          onClick={closeMobileMenu}
        >
          <TrackChangesRoundedIcon fontSize="large" className="header__icon" />
          Site Name
        </Link>

        <div className="header__menuIcon" onClick={handleClick}>
          {click ? (
            <CloseRoundedIcon fontSize="large" />
          ) : (
            <MenuRoundedIcon fontSize="large" />
          )}
        </div>
        <ul className={click ? "header__menu active" : "header__menu"}>
          <li className="header__item">
            <Link
              to={ROUTES.HOME}
              className="header__links"
              onClick={closeMobileMenu}
            >
              Home
            </Link>
          </li>
          <li className="header__item">
            <Link
              to={ROUTES.PATHS}
              className="header__links"
              onClick={closeMobileMenu}
            >
              Paths
            </Link>
          </li>
          <li className="header__item">
            <Link
              to={ROUTES.HOME}
              className="header__links"
              onClick={closeMobileMenu}
            >
              Featured
            </Link>
          </li>
          <li className="header__item">
            <Link
              to={ROUTES.HOME}
              className="header__links"
              onClick={closeMobileMenu}
            >
              Contact us
            </Link>
          </li>
          {authContext.isAuthenticated() ? (
            <AvatarDropdown />
          ) : (
            <>
              {/* Buttons Sign In and Login */}
              <li className="header__button">
                {button ? (
                  <Link
                    to={ROUTES.SIGN_UP}
                    className="header__buttonLink"
                    onClick={closeMobileMenu}
                  >
                    <button className="header__buttonSignUp">Sign Up</button>
                  </Link>
                ) : (
                  <Link to={ROUTES.SIGN_UP} className="header__buttonLink">
                    <button className="header__buttonSignUpMobile">
                      Sign Up
                    </button>
                  </Link>
                )}
              </li>
              <li className="header__button">
                {button ? (
                  <Link
                    to={ROUTES.SIGN_IN}
                    className="header__buttonLink"
                    onClick={closeMobileMenu}
                  >
                    <button className="header__buttonSignUp">Login</button>
                  </Link>
                ) : (
                  <Link to={ROUTES.SIGN_IN} className="header__buttonLink">
                    <button className="header__buttonSignUpMobile">
                      Login
                    </button>
                  </Link>
                )}
              </li>
            </>
          )}
          {/* Check is user authenticated and is an admin, then show the
          navigation item */}
          {authContext.isAuthenticated() &&
          authContext.authState.user.roles === "ROLE_ADMIN" ? (
            <li className="header__admin">
              <Link
                to={`${ROUTES.ADMIN_PANEL}/admin-home`}
                className="header__links"
                onClick={closeMobileMenu}
              >
                A-Panel
              </Link>
            </li>
          ) : null}
        </ul>
      </div>
    </div>
  );
}

export default Header;
