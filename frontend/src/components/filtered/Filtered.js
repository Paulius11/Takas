import React from "react";
import "./Filtered.css";
import { PathContext } from "../../context/PathProvider";
import { useContext } from "react";

function Filtered() {
  const {
    filters: { search, rating, region, length, difficulty },
    updateFilters,
    sorted,
  } = useContext(PathContext);
  return (
    <div className="filtered">
      <h2>Paths: {sorted.flat().length}</h2>
      <hr />
      <h1>Filters:</h1>
      <input
        placeholder="Search title"
        type="text"
        name="search"
        value={search}
        onChange={updateFilters}
      ></input>
      {/* <form>
        <h3>Rating</h3>
        1 <input type="checkbox" value />
        2 <input type="checkbox" />
        3 <input type="checkbox" />
        4 <input type="checkbox" />
        5 <input type="checkbox" />
      </form> */}
      <form>
        <h3>Region</h3>
        <select name="region" value={region} onChange={updateFilters}>
          <option value="all">All</option>
          <option value="Kaunas park">Kaunas</option>
          <option value="Vilnius">Vilnius</option>
          <option value="Vilnius Park">Vilnius Park</option>
          <option value="Mist Park">Mist Park</option>
          <option value="Right Park">Right Park</option>
        </select>
      </form>
      <form>
        <h3>Difficulty</h3>
        <select name="difficulty" value={difficulty} onChange={updateFilters}>
          <option value="all">All</option>
          <option value="easy">Easy</option>
          <option value="medium">Medium</option>
          <option value="hard">Hard</option>
        </select>
      </form>
      <form>
        <h3>Lenght</h3>
        <label>
          <input
            type="radio"
            name="length"
            value="all"
            checked={length === "all"}
            onChange={updateFilters}
          />
          all
        </label>
        <br />
        <label>
          <input
            type="radio"
            name="length"
            value="0"
            checked={length === 0}
            onChange={updateFilters}
          />
          0 - 10
        </label>
        <br />
        <label>
          <input
            type="radio"
            name="length"
            value="10"
            checked={length === 10}
            onChange={updateFilters}
          />
          10 - 30
        </label>
        <br />
        <label>
          <input
            type="radio"
            name="length"
            value="30"
            checked={length === 30}
            onChange={updateFilters}
          />
          over 30
        </label>
      </form>
    </div>
  );
}

export default Filtered;
