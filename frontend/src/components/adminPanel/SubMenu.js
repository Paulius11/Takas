import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./SubMenu.css";

const SubMenu = ({ item }) => {
  const [subnav, setSubnav] = useState(false);

  const showSubnav = () => setSubnav(!subnav);

  return (
    <>
      <Link
        className="subMenu__link"
        to={item.path}
        onClick={item.subNav && showSubnav}
      >
        <div className="subMenu__linkContainer">
          <div className="subMenu__menuIcon">{item.icon}</div>
          <span className="subMenu__sidebarLabel">{item.title}</span>
        </div>
        <div>
          {item.subNav && subnav
            ? item.iconOpened
            : item.subNav
            ? item.iconClosed
            : null}
        </div>
      </Link>
      {subnav &&
        item.subNav.map((item, index) => {
          return (
            <Link className="subMenu__dropdownLink" to={item.path} key={index}>
              {item.icon}
              <span className="subMenu__sidebarLabel">{item.title}</span>
            </Link>
          );
        })}
    </>
  );
};

export default SubMenu;
