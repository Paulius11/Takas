import axios from "axios";
import React, { useContext, useState } from "react";
import { Link } from "react-router-dom";
import { PathContext } from "../../context/PathProvider";
import Cookies from "js-cookie";

axios.interceptors.request.use((config) => {
  config.headers.authorization = "Bearer " + Cookies.get("token");
  return config;
});

function AdminPanel() {
  const { paths } = useContext(PathContext);
  const initialState = {
    title: "",
    description: "",
  };

  const [newPath, setNewPath] = useState(initialState);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setNewPath({ ...newPath, [name]: value });
  };

  const handlePathSubmit = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/article/create", newPath)
      .then((response) => {
        setNewPath({
          title: response.data.title,
          description: response.data.description,
        });
      })
      .catch((error) => {
        console.log("Error - " + error);
      });
  };

  return (
    <>
      <h1>Add Path</h1>
      <form>
        <h5>Path title</h5>
        <input
          type="text"
          placeholder="Enter path name"
          value={newPath.title || ""}
          onChange={handleInputChange}
          name="title"
        />
        <h5>Description</h5>
        <textarea
          type="text"
          placeholder="Enter the description"
          value={newPath.description || ""}
          onChange={handleInputChange}
          name="description"
        />
        <div>
          <button
            style={{ background: "red" }}
            type="submit"
            onClick={handlePathSubmit}
          >
            Add
          </button>
        </div>
      </form>
      {paths.map((path) => {
        return (
          <p key={path.id}>
            {path.id}: {path.title}
          </p>
        );
      })}
      <h1>Admin panel</h1>
      <Link to="/">Website</Link>
    </>
  );
}

export default AdminPanel;
