import React from "react";
import { Link } from "react-router-dom";
import "./AdminHome.css";

function AdminHomeCard({ item }) {
  const { title, desc, url, icon } = item;
  return (
    <div className="adminHome__card">
      <div className="card__container">
        <span>{icon}</span>
        <h3>{title}</h3>
        <p>{desc}</p>
        <Link to={url}>
          <span>{title}</span>
        </Link>
      </div>
    </div>
  );
}

export default AdminHomeCard;
