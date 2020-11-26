import React, { createContext, useEffect } from "react";
import { useState } from "react";
import { paginate } from "../utils/helpers";
import { BASE_URL } from "../utils/URL";
import Axios from "axios";

export const PathContext = createContext();

export default function PathProvider({ children }) {
  const [loading, setLoading] = useState(false);
  const [paths, setPaths] = useState([]);
  const [sorted, setSorted] = useState([]);
  const [page, setPage] = useState(0);
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
    let filterValue;
    if (type === "checkbox") {
      filterValue = e.target.checked;
    } else if (type === "radio") {
      value === "all" ? (filterValue = value) : (filterValue = parseInt(value));
    } else {
      filterValue = value;
    }
    setFilters({ ...filters, [filter]: filterValue });
  };

  useEffect(() => {
    let newPaths = [...paths].sort((a, b) => a.length - b.length);
    const { search, region, rating, length, difficulty } = filters;

    if (region !== "all") {
      newPaths = newPaths.filter((item) => item.region === region);
    }

    if (difficulty !== "all") {
      newPaths = newPaths.filter((item) => item.difficulty === difficulty);
    }

    if (search !== "") {
      newPaths = newPaths.filter((item) => {
        let title = item.title.toLowerCase().trim();
        return title.includes(search) ? item : null;
      });
    }
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

    setPage(0);
    setSorted(paginate(newPaths));
  }, [filters, paths]);

  useEffect(() => {
    setLoading(true);
    Axios.get(BASE_URL).then((response) => {
      setSorted(paginate(response.data));
      setPaths(response.data);
      setLoading(false);
    });

    return () => {};
  }, []);

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
