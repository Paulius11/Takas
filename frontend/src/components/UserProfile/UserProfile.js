import React, { useContext } from "react";
import { AuthContext } from "../../context/AuthContext";

function UserProfile() {
  const authContext = useContext(AuthContext);

  const { jwt, user } = authContext.authState;

  return (
    <div>
      <h1>User Profile</h1>
      <br />
      <p>
        <strong>Username</strong>
        {user.username}
      </p>
      <p>
        <strong>Email</strong>
        {user.email}
      </p>
      <p>
        <strong>Role</strong>
        {user.roles}
      </p>
      <p>
        <strong>Token</strong>
        {jwt}
      </p>
    </div>
  );
}

export default UserProfile;
