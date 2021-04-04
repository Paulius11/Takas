import React, { useContext } from "react";
import { SidebarData } from "./../../data/AdminSidebarData";
import SubMenu from "./SubMenu";
import "./AdminSidebar.css";
import { AuthContext } from "../../context/AuthContext";
import KeyboardReturnIcon from "@material-ui/icons/KeyboardReturn";
import { Link } from "react-router-dom";
import * as ROUTES from "./../../utils/routes";

const AdminSidebar = () => {
  const auth = useContext(AuthContext);
  const { authState } = auth;

  return (
    <div className="aSidebar__container">
      <div className="aSidebar__nav">
        <h2 className="aSidebar__logo">
          <Link to={ROUTES.HOME} title="Return to Webpage">
            <KeyboardReturnIcon />
          </Link>
          Admin Panel
        </h2>
        <div className="aSidebar__user">
          <div>
            <img
              src="https://www.searchpng.com/wp-content/uploads/2019/02/User-Icon-PNG.png"
              alt="User"
            />
          </div>
          <div>
            <h4>{authState.user.username}</h4>
            <p>{authState.user.roles === "ROLE_ADMIN" && <span>Admin</span>}</p>
          </div>
        </div>
        {SidebarData.map((item, index) => {
          return <SubMenu item={item} key={index} />;
        })}
      </div>
    </div>
  );
};

export default AdminSidebar;
