import React, {useState } from "react";
import axios from "axios";
import { useCookies } from "react-cookie";


const BASE_URL = "http://localhost:8080";
const USERS = "/api/user/details";

const REDIRECT_URL = "http://localhost:3000";
const OAUT2URL = "http://localhost:8080/oauth2/authorization/google";


export default function ReactLogin() {
  const [cookies, setCookie, removeCookie] = useCookies(["name"]);
  const [userData, setUserData] = useState([]);

const setRedirectUrlCookie = () => {
  setCookie('successfulLoginRedirectUrl', REDIRECT_URL, { path: '/' });
}

 const getUserData = () =>{
   console.log(cookies.successfulLoginRedirectUrl);
   console.log(cookies.jwt);
   console.log(cookies);

  axios
  .get(BASE_URL+ USERS, { headers: { Authorization: `Bearer ${cookies.jwt}`} })
  .then((response) => {
    console.log("Geting user data")
    console.log(response.data);
    console.log(response.status);
    console.log(response.statusText);
    console.log(response.headers);
    console.log(response.config);
    setUserData(JSON.stringify(response.data))

  })
  .catch((error) => {
    console.log(error);
    setUserData(error)

  });
  }

  return (
    <>
      <h2>Google Sign In Demo (Frontend)</h2>
      <a onClick={setRedirectUrlCookie} href={OAUT2URL}>Google sign in</a>
      <p>
        Kad redirectintų reikia uždėti sausainį su pavadinimu "successfulLoginRedirectUrl"<br/>
        jwt saugomas sausainyje pavadinimu "jwt"
      </p>
      <br></br>
      <br></br>
      <button onClick={ () => getUserData()}> Get User data</button>
      <br></br>
      <br></br>
      <textarea defaultValue={userData} />
    </>
  );
}
