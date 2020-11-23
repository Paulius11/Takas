import React from "react";
import "./Filtered.css";

function Filtered() {
  return (
    <div className="filtered">
      <h1>Filters:</h1>
      <input placeholder="Search" />
      <form>
        <h3>Rating</h3>
        <input type="checkbox" /> 5
        <input type="checkbox" /> 4
        <input type="checkbox" /> 3
        <input type="checkbox" /> 2
        <input type="checkbox" /> 1
      </form>
      <form>
        <h3>Region</h3>
        <select>
          <option value="all">All</option>
          <option value="Kaunas">Kaunas</option>
          <option value="Vilnius">Vilnius</option>
          <option value="Mist">Mist Park</option>
          <option value="Right">Right Park</option>
        </select>
      </form>
      <form>
        <h3>Lenght</h3>
      </form>
    </div>
  );
}

export default Filtered;
