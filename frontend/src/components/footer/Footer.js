import React from "react";
import "./Footer.css";
import { Link } from "react-router-dom";

function Footer() {
  return (
    <div className="footer">
      <div className="footer__container">
        <h2>Questions? Contact Us.</h2>
        <div className="footer__row">
          <div className="footer__column">
            <Link to="">Top 10 Paths</Link>
            <Link to="">Most Popular Paths</Link>
            <Link to="">All Paths</Link>
            <Link to="">The beginner's Paths</Link>
          </div>
          <div className="footer__column">
            <Link to="">Take a hike</Link>
            <Link to="">Hiking destinations</Link>
            <Link to="">Hiking Essentials</Link>
          </div>
          <div className="footer__column">
            <Link to="">FAQ</Link>
            <Link to="">Advertise</Link>
            <Link to="">Sign In</Link>
            <Link to="">Sign Up</Link>
          </div>
          <div className="footer__column">
            <Link to="">Contanct us</Link>
            <Link to="">About</Link>
            <Link to="">Support us</Link>
            <Link to="">Terms</Link>
            <Link to="">Privacy</Link>
          </div>
        </div>
      </div>
      <p>© Copyright 2020. All rights reserved.</p>
    </div>
  );
}

export default Footer;
