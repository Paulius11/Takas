import React, { createContext, useEffect } from "react";
import { useState } from "react";
import { paginate } from "../utils/helpers";
import { BASE_URL } from "../utils/URL";
import Axios from "axios";

// Context for paths that you can get paths form anywhere without prop drilling
export const PathContext = createContext();

export default function PathProvider({ children }) {
  const [loading, setLoading] = useState(false);
  const [paths, setPaths] = useState([]);
  // extra state values
  const [sorted, setSorted] = useState([]);
  const [page, setPage] = useState(0);
  // Setting up as an object that deals with all the filters(item:initial value)
  const [filters, setFilters] = useState({
    search: "",
    region: "all",
    rating: 5,
    length: "all",
    difficulty: "all",
  });

  const changePage = (index) => {
    setPage(index);
  };
  const updateFilters = (e) => {
    const type = e.target.type;
    const filter = e.target.name;
    const value = e.target.value;
    // For checkbox and radio to work
    let filterValue;
    if (type === "checkbox") {
      filterValue = e.target.checked;
    } else if (type === "radio") {
      value === "all" ? (filterValue = value) : (filterValue = parseInt(value));
    } else {
      filterValue = value;
    }
    // First coping everything whats in the filters, then oeverrite the values
    setFilters({ ...filters, [filter]: filterValue });
  };

  // Renders everytime filters change
  useEffect(() => {
    //Copy the paths and sort them by path length
    let newPaths = [...paths].sort((a, b) => a.length - b.length);
    const { search, region, rating, length, difficulty } = filters;

    // Checking paths by region
    if (region !== "all") {
      newPaths = newPaths.filter((item) => item.region === region);
    }
    // Checking paths by difficulty
    if (difficulty !== "all") {
      newPaths = newPaths.filter((item) => item.difficulty === difficulty);
    }
    //Checking is the title exist
    if (search !== "") {
      newPaths = newPaths.filter((item) => {
        let title = item.title.toLowerCase().trim();
        return title.includes(search) ? item : null;
      });
    }
    //Checking path length for filtering
    if (length !== "all") {
      newPaths = newPaths.filter((item) => {
        if (length === 0) {
          return item.length < 10;
        } else if (length === 10) {
          return item.length >= 10 && item.length < 30;
        } else {
          return item.length >= 30;
        }
      });
    }
    //Every time we filter go to the first page
    setPage(0);
    //Return filtered paths
    setSorted(paginate(newPaths));
  }, [filters, paths]);

  //Getting paths from back
  useEffect(() => {
    setLoading(true);
    Axios.get(BASE_URL).then((response) => {
      setSorted(paginate(response.data));
      setPaths(response.data);
      setLoading(false);
    });

    return () => {};
  }, []);

  // Returning children in this case it's <App />, value it's props(functions, objetc and etc.)
  return (
    <PathContext.Provider
      value={{
        paths,
        loading,
        page,
        filters,
        changePage,
        updateFilters,
        sorted,
      }}
    >
      {children}
    </PathContext.Provider>
  );
}
