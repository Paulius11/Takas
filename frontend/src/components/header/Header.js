import React, { useState, useEffect } from "react";
import "./Header.css";
import { Link } from "react-router-dom";
import MenuRoundedIcon from "@material-ui/icons/MenuRounded";
import TrackChangesRoundedIcon from "@material-ui/icons/TrackChangesRounded";
import CloseRoundedIcon from "@material-ui/icons/CloseRounded";

function Header() {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);

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
  }, []);

  window.addEventListener("resize", showButton);

  return (
    <div className="header">
      <div className="header__container container">
        <Link to="/" className="header__logo" onClick={closeMobileMenu}>
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
            <Link to="/" className="header__links" onClick={closeMobileMenu}>
              Home
            </Link>
          </li>
          <li className="header__item">
            <Link
              to="/paths"
              className="header__links"
              onClick={closeMobileMenu}
            >
              Paths
            </Link>
          </li>
          <li className="header__item">
            <Link to="/" className="header__links" onClick={closeMobileMenu}>
              Featured
            </Link>
          </li>
          <li className="header__item">
            <Link to="/" className="header__links" onClick={closeMobileMenu}>
              Contact us
            </Link>
          </li>
          <li className="header__button">
            {button ? (
              <Link
                to="/signup"
                className="header__buttonLink"
                onClick={closeMobileMenu}
              >
                <button className="header__buttonSignUp">Sign Up</button>
              </Link>
            ) : (
              <Link to="/signup" className="header__buttonLink">
                <button className="header__buttonSignUpMobile">Sign Up</button>
              </Link>
            )}
          </li>
        </ul>
      </div>
    </div>
  );
}

export default Header;
