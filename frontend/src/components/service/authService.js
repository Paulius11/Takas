import axios from "axios";
import jwt_decode from "jwt-decode";

axios.interceptors.request.use((config) => {
  config.headers.authorization = getToken();
  return config;
});

export const login = (username, password) => {
  try {
    axios
      .post("http://localhost:8080/api/user/login", username, password)
      .then((res) => storeToken(res.data.jwt))
      .then(() => window.location.replace("/paths"));
  } catch (err) {
    console.log(err);
  }
};

const storeToken = (token) => {
  if (token !== "") {
    localStorage.setItem("token", token);
  }
};

function getToken() {
  const token = localStorage.getItem("token");
  if (token !== "") {
    return "Bearer " + localStorage.getItem("token");
  }
}

const logout = () => {
  localStorage.clear();
  //need to add redirect
};

function validateToken(token) {
  if (token !== "") {
    const decoded_token = jwt_decode(token);
    console.log(decoded_token);
    if (decoded_token.exp <= Date.now()) {
      return true;
    }
    return false;
  }
}
