import React, { useContext, useEffect, useRef, useState } from "react";
import { AuthContext } from "../../context/AuthContext";
import ExitToAppIcon from "@material-ui/icons/ExitToApp";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import Icon from "@material-ui/core/Icon";
import "./AvatarDropdown.css";

const DropdownItem = ({ item }) => (
  <button className="dropdownItem" onClick={item.onClick}>
    <Icon> {item.icon}</Icon>
    <p>{item.title}</p>
  </button>
);

const DropdownContent = ({ dropdownItems }) => {
  return (
    <div className="dropdownContent">
      {dropdownItems.map((item, i) => {
        return (
          <div key={i}>
            <DropdownItem item={item} />
          </div>
        );
      })}
    </div>
  );
};

const AvatarDropdown = () => {
  const node = useRef();
  const auth = useContext(AuthContext);
  const { authState } = auth;
  const [dropdownOpen, setDropdownOpen] = useState(false);

  const dropdownItems = [
    {
      title: "Log Out",
      icon: <ExitToAppIcon />,
      onClick: auth.logout,
    },
  ];

  const handleClick = (e) => {
    if (!node.current.contains(e.target)) {
      setDropdownOpen(false);
    }
  };

  useEffect(() => {
    document.addEventListener("mousedown", handleClick);

    return () => {
      document.removeEventListener("mousedown", handleClick);
    };
  }, []);

  return (
    <div ref={node}>
      <button
        ref={node}
        className="avatarButton"
        onClick={() => setDropdownOpen(!dropdownOpen)}
      >
        <div>
          <p>{authState.user.username}</p>
        </div>
        <div>
          <ExpandMoreIcon />
        </div>
      </button>
      {dropdownOpen && (
        <div className="relative">
          <DropdownContent dropdownItems={dropdownItems} />
        </div>
      )}
    </div>
  );
};

export default AvatarDropdown;
