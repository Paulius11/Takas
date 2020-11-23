import React, { createContext, useEffect } from "react";
import { useState } from "react";
import pathData from "../data/pathData.json";
import { paginate } from "../utils/helpers";

export const PathContext = createContext();

export default function PathProvider({ children }) {
  const [loading, setLoading] = useState(false);
  const [paths, setPaths] = useState([]);
  const [sorted, setSorted] = useState([]);
  const [page, setPage] = useState(0);
  const [filters, setFilters] = useState({
    search: "",
    region: "all",
    rating: "",
    lenght: "all",
    difficulty: "all",
  });

  const changePage = (index) => {
    setPage(index);
  };
  const updateFilters = (e) => {};

  useEffect(() => {
    setLoading(true);
    setSorted(paginate(pathData));
    setPaths(pathData);
    setLoading(false);
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
