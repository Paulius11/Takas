import React, { useContext } from "react";
import { AuthContext } from "../../context/AuthContext";
import "./UserProfile.css";
import RoomOutlinedIcon from "@material-ui/icons/RoomOutlined";
import EventAvailableOutlinedIcon from "@material-ui/icons/EventAvailableOutlined";

function UserProfile() {
  const authContext = useContext(AuthContext);
  const { user } = authContext.authState;
  const { username, title, favorites, created_at } = user;

  return (
    <div className="userProfile">
      <div className="userProfile__container">
        <img
          src="https://static.boredpanda.com/blog/wp-content/uploads/2020/08/12-5f43a840276d8__700.jpg"
          alt={title}
        />
        <h2>{username}</h2>
        <div className="userProfile__info">
          <p>
            <RoomOutlinedIcon />
            Location:
            <span>Vilnius,</span>
          </p>
          <p>
            <EventAvailableOutlinedIcon />
            Join Date:
            <span>{created_at.slice(0, 10)}</span>
          </p>
        </div>
        <div className="userProfile__stats">
          <div className="stats__container">
            <h2>{favorites !== null ? favorites.length : 0}</h2>
            <p>Favorites</p>
          </div>
          <div className="stats__container">
            <h2>14</h2>
            <p>Wish to Walk</p>
          </div>
          <div className="stats__container">
            <h2>5</h2>
            <p>Walked</p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default UserProfile;
