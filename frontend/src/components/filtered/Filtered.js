import React, { useContext } from "react";
import "./Filtered.css";
import { PathContext } from "../../context/PathProvider";
import SearchIcon from "@material-ui/icons/Search";

function Filtered() {
  const {
    filters: { search, region, length, difficulty },
    updateFilters,
    sorted,
  } = useContext(PathContext);
  return (
    <div className="filtered">
      <div>
        <h2>Paths found: {sorted.flat().length}</h2>
        <hr />
        <br />
        <div className="filtered__search">
          <SearchIcon />
          <input
            placeholder="Search for title"
            type="text"
            name="search"
            value={search}
            onChange={updateFilters}
            autoComplete="off"
          />
        </div>
      </div>
      <br />
      <div>
        <h3>Region</h3>
        <select
          className="filtered__select"
          name="region"
          value={region}
          onChange={updateFilters}
        >
          <option value="all">All</option>
          <option value="Kaunas park">Kaunas</option>
          <option value="Vilnius park">Vilnius</option>
          <option value="Klaipėda park">Klaipėda</option>
        </select>
      </div>
      <div>
        <h3>Difficulty</h3>
        <select
          className="filtered__select"
          name="difficulty"
          value={difficulty}
          onChange={updateFilters}
        >
          <option value="all">All</option>
          <option value="easy">Easy</option>
          <option value="medium">Medium</option>
          <option value="hard">Hard</option>
        </select>
      </div>
      <div>
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
          0 - 10 km
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
          10 - 30 km
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
          over 30 km
        </label>
      </div>
    </div>
  );
}

export default Filtered;
