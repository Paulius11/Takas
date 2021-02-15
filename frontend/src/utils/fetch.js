import axios from "axios";

const publicFetch = axios.create({
  baseURL: "http://localhost:8080/api/user",
});

export { publicFetch };
