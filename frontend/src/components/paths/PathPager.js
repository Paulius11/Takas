import React from "react";
import { PathContext } from "../../context/PathProvider";
import { useContext } from "react";
import PathList from "./PathList";
import "./PathPager.css";
import ChevronLeftIcon from "@material-ui/icons/ChevronLeft";
import ChevronRightIcon from "@material-ui/icons/ChevronRight";

function PathPager() {
  const { sorted, page, changePage } = useContext(PathContext);
  if (sorted[page]) {
    return (
      <>
        <PathList paths={sorted[page]} />
        {sorted.length > 1 && (
          <article>
            {page > 0 && (
              <button onClick={() => changePage(page - 1)}>
                <ChevronLeftIcon />
              </button>
            )}
            {sorted.map((_, index) => {
              return (
                <button
                  onClick={() => changePage(index)}
                  key={index}
                  className={`page-btn ${page === index && `page-btn-current`}`}
                >
                  {index + 1}
                </button>
              );
            })}
            {page < sorted.length - 1 && (
              <button onClick={() => changePage(page + 1)}>
                <ChevronRightIcon />
              </button>
            )}
          </article>
        )}
      </>
    );
  } else {
    return <h3>No paths found</h3>;
  }
}

export default PathPager;
