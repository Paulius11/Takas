import axios from "axios";
import React, { useState } from "react";
import { BASE_URL } from "./../../../utils/URL";
import "./AddPath.css";

function AddPath() {
  const initialValue = {
    title: "",
    image: "",
    description: "",
    featured: false,
    length: "",
    difficulty: "HARD",
    region: "VILNIUS",
  };

  const [newPath, setNewPath] = useState(initialValue);

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post(`${BASE_URL}/private/create`, newPath)
      .then((response) => {
        setNewPath(response.data);
        console.log("Success");
      })
      .then(() => window.location.replace("/admin-panel/data/paths"))
      .catch((error) => {
        console.log("Error - " + error);
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
      <h1>Add path</h1>
      <div className="underline"></div>
      <form onSubmit={handleSubmit}>
        <div className="input__container">
          <label htmlFor="title">Title</label>
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
          <label htmlFor="title">Image</label>
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
          <label htmlFor="length">Length</label>
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
        <div className="input__container">
          <label htmlFor="difficulty">Difficulty</label>
          {difficulty.map((option) => {
            return (
              <div className="radio__toolbar">
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
          {regions.map((region) => {
            return (
              <div className="radio__toolbar">
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
            Description
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
  { value: "MARIAMPOLĖ", label: "Marijampolė" },
  { value: "PANEVEŽYS", label: "Panevėžys" },
  { value: "ŠIAULIAI", label: "Šiauliai" },
  { value: "TAURAGĖ", label: " Taurgė" },
  { value: "TELŠIAI", label: "Telšiai" },
  { value: "UTENA", label: "Utena" },
];

export default AddPath;
