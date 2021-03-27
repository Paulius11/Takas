import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { BASE_URL } from "./../../../utils/URL";
import "./AddPath.css";
import Alert from "./Alert";

function AddPath() {
  const { id } = useParams();
  const initialValue = {
    title: "",
    image: "",
    description: "",
    featured: false,
    length: "",
    difficulty: "EASY",
    region: "VILNIUS",
    published: true,
    rating: "",
  };

  const [newPath, setNewPath] = useState(initialValue);
  const [alert, setAlert] = useState({ show: false, msg: "", type: "" });

  const showAlert = (show = false, type = "", msg = "") => {
    setAlert({ show, type, msg });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (
      !newPath.title.trim() ||
      !newPath.description.trim() ||
      !newPath.image ||
      !newPath.length
    ) {
      showAlert(true, "danger", "please enter required values");
    } else {
      axios
        .post(`${BASE_URL}/private/create`, newPath)
        .then((response) => {
          setNewPath(response.data);
          showAlert(true, "success", "new path is added successfuly");
        })
        .then((response) => {
          setNewPath(initialValue);
        })
        .catch((error) => {
          console.log("Error - " + error);
          showAlert(true, "danger", "something went wrong");
        });
    }
  };

  // Get the path by id
  useEffect(() => {
    const subscribe = axios
      .get(`${BASE_URL}/${id}`)
      .then((res) => {
        if (res.data !== null) {
          setNewPath({
            title: res.data.title,
            image: res.data.image,
            description: res.data.description,
            difficulty: res.data.difficulty,
            length: res.data.length,
            region: res.data.region,
            featured: res.data.featured,
            published: res.data.published,
            rating: res.data.rating,
          });
          console.log("update", newPath);
          console.log(res.data);
        }
      })
      .catch((error) => {
        console.error("Error - " + error);
      });
    return () => setNewPath(subscribe); // to do
  }, []);

  const updatePath = (e) => {
    e.preventDefault();
    const path = {
      title: newPath.title,
      image: newPath.image,
      description: newPath.description,
      difficulty: newPath.difficulty,
      length: newPath.length,
      region: newPath.region,
      featured: newPath.featured,
      published: newPath.published,
      rating: newPath.rating,
    };

    axios.put(`${BASE_URL}/${id}`, path).then((res) => {
      setNewPath(res.data);
      showAlert(true, "success", "new path was successfuly edited");
    });
  };

  const handleOnChange = (event) => {
    const target = event.target;
    const value = target.type === "checkbox" ? target.checked : target.value;
    const name = target.name;
    setNewPath({ ...newPath, [name]: value });
  };

  const resetForm = (e) => {
    e.preventDefault();
    setNewPath(initialValue);
  };

  return (
    <div className="addPath__form">
      {alert.show && <Alert {...alert} removeAlert={showAlert} />}
      <h1>{id ? `Edit Path ${id}` : "Add Path"}</h1>
      <div className="underline"></div>
      <form onSubmit={id ? updatePath : handleSubmit}>
        <div className="input__container">
          <label htmlFor="title">Title*</label>
          <input
            id="title"
            type="text"
            value={newPath.title}
            onChange={handleOnChange}
            name="title"
            className="title"
          />
        </div>
        <div className="input__container image">
          <label htmlFor="title">Image*</label>
          <input
            id="image"
            type="text"
            value={newPath.image}
            onChange={handleOnChange}
            name="image"
          />
          <img
            src={
              newPath.image ||
              "https://media.timeout.com/images/105645687/image.jpg"
            }
            alt={newPath.title}
          />
        </div>
        <div className="input__container">
          <label htmlFor="length">Length*</label>
          <input
            id="length"
            type="text"
            value={newPath.length}
            onChange={handleOnChange}
            name="length"
            className="length"
          />
        </div>
        <div className="input__container checkbox">
          <label htmlFor="featured">Featured</label>
          <input
            type="checkbox"
            value={newPath.featured}
            placeholder="featured"
            onChange={handleOnChange}
            name="featured"
            checked={newPath.featured}
          />
        </div>
        {id && (
          <div className="input__container checkbox">
            <label htmlFor="published">Published</label>
            <input
              type="checkbox"
              value={newPath.published}
              placeholder="published"
              onChange={handleOnChange}
              name="published"
              checked={newPath.published}
            />
          </div>
        )}
        <div className="input__container">
          <label htmlFor="difficulty">Difficulty</label>
          {difficulty.map((option, index) => {
            return (
              <div className="radio__toolbar" key={index}>
                <input
                  type="radio"
                  value={option.value}
                  onChange={handleOnChange}
                  checked={newPath.difficulty === option.value}
                  name="difficulty"
                  id={option.label}
                />
                <label htmlFor={option.label}>{option.label}</label>
              </div>
            );
          })}
        </div>
        <div className="input__container">
          <label htmlFor="region">Region</label>
          {regions.map((region, index) => {
            return (
              <div className="radio__toolbar" key={index}>
                <input
                  type="radio"
                  value={region.value}
                  onChange={handleOnChange}
                  checked={newPath.region === region.value}
                  name="region"
                  id={region.label}
                />
                <label htmlFor={region.label}>{region.label}</label>
              </div>
            );
          })}
        </div>
        <div className="input__container">
          <label htmlFor="description" className="description__label">
            Description*
          </label>
          <textarea
            id="description"
            type="text"
            value={newPath.description}
            onChange={handleOnChange}
            name="description"
            className="description"
          />
        </div>

        <div className="buttons__container">
          <button type="submit">Submit</button>
          <button type="reset" onClick={resetForm}>
            Reset
          </button>
          <Link to="/admin-panel/data/paths">Paths</Link>
        </div>
      </form>
    </div>
  );
}

const difficulty = [
  { value: "EASY", label: "Easy" },
  { value: "MEDIUM", label: "Medium" },
  { value: "HARD", label: "Hard" },
];

const regions = [
  { value: "VILNIUS", label: "Vilnius" },
  { value: "KAUNAS", label: "Kaunas" },
  { value: "KLAIPĖDA", label: "Klaipėda" },
  { value: "MARIJAMPOLĖ", label: "Marijampolė" },
  { value: "PANEVEŽYS", label: "Panevėžys" },
  { value: "ŠIAULIAI", label: "Šiauliai" },
  { value: "TAURAGĖ", label: " Taurgė" },
  { value: "TELŠIAI", label: "Telšiai" },
  { value: "UTENA", label: "Utena" },
];

export default AddPath;
