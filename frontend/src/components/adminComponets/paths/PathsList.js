import React, { useEffect, useState } from "react";
import Cookies from "js-cookie";
import axios from "axios";
import DataTable from "react-data-table-component";
import { BASE_URL } from "../../../utils/URL";
import DeleteIcon from "@material-ui/icons/Delete";
import { Link } from "react-router-dom";
import AddIcon from "@material-ui/icons/Add";
import "./AddPath.css";
import { IconButton } from "@material-ui/core";
import CheckIcon from "@material-ui/icons/Check";
import ClearIcon from "@material-ui/icons/Clear";
import DeleteModal from "./DeleteModal";

axios.interceptors.request.use((config) => {
  config.headers.authorization = "Bearer " + Cookies.get("token");
  return config;
});

//For path search
const FilterComponent = ({ filterText, onFilter, onClear }) => (
  <>
    <input
      type="text"
      placeholder="Filter By Name"
      value={filterText}
      onChange={onFilter}
      className="path__search"
    />
    <button type="button" onClick={onClear} className="search__button">
      X
    </button>
  </>
);

function PathsList() {
  const [adminPaths, setAdminPaths] = useState([]);
  const [filterText, setFilterText] = useState("");
  const [resetPaginationToggle, setResetPaginationToggle] = useState(false);
  const [open, setOpen] = React.useState(false);
  //setting id for delete modal
  const [id, setId] = useState(0);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  useEffect(() => {
    axios.get(`${BASE_URL}/private/all`).then((response) => {
      setAdminPaths(response.data);
      console.log(response.data);
    });
  }, []);

  const handleDelete = (id) => {
    axios.delete(`${BASE_URL}/${id}`).then((result) => {
      result.data = adminPaths.filter((path) => path.id !== id);
      setAdminPaths(result.data);
      handleClose();
      console.log("Delete was successful", id);
    });
  };

  const columns = [
    {
      name: "Del",
      width: "50px",
      selector: "id",
      center: true,
      cell: (row) => (
        <>
          <IconButton
            onClick={() => {
              handleOpen();
              setId(row.id);
            }}
          >
            <DeleteIcon title="Delete" />
          </IconButton>
        </>
      ),
    },
    {
      name: "ID",
      width: "62px",
      selector: "id",
      sortable: true,
      cell: (row) => <div>{row.id}</div>,
    },
    {
      name: "Image",
      width: "70px",
      selector: "image",
      sortable: true,
      center: true,
      cell: (row) => (
        <img alt={row.title} style={{ width: "60px" }} src={row.image} />
      ),
    },
    {
      name: "Title",
      width: "140px",
      selector: "title",
      sortable: true,
      center: true,
      cell: (row) => <div>{row.title}</div>,
    },
    {
      name: "Description",
      width: "300px",
      selector: "description",
      sortable: true,
      center: true,
      cell: (row) => <div>{row.description.substring(0, 40)}...</div>,
    },
    {
      name: "Length",
      width: "70px",
      selector: "title",
      sortable: true,
      center: true,
      cell: (row) => <div>{row.length}</div>,
    },
    {
      name: "Difficulty",
      width: "84px",
      selector: "difficulty",
      sortable: true,
      center: true,
      cell: (row) => <div>{row.difficulty}</div>,
    },

    {
      name: "Rating",
      width: "70px",
      selector: "rating",
      sortable: true,
      center: true,
      cell: (row) => <div>{row.rating}</div>,
    },
    {
      name: "Region",
      selector: "region",
      width: "90px",
      sortable: true,
      center: true,
      cell: (row) => <div>{row.region}</div>,
    },
    {
      name: "Featured",
      width: "70px",
      selector: "featured",
      sortable: true,
      center: true,
      cell: (row) => (
        <div>
          {row.featured === true ? (
            <CheckIcon style={{ color: "#0DA462" }} />
          ) : (
            <ClearIcon style={{ color: "#EA5252" }} />
          )}
        </div>
      ),
    },
    {
      name: "Published",
      width: "70px",
      selector: "published",
      sortable: true,
      center: true,
      cell: (row) => (
        <div>
          {row.published === true ? (
            <CheckIcon style={{ color: "#0DA462" }} />
          ) : (
            <ClearIcon style={{ color: "#EA5252" }} />
          )}
        </div>
      ),
    },
  ];

  const filteredItems = adminPaths.filter(
    (item) =>
      item.title && item.title.toLowerCase().includes(filterText.toLowerCase())
  );

  const subHeaderComponentMemo = React.useMemo(() => {
    const handleClear = () => {
      if (filterText) {
        setResetPaginationToggle(!resetPaginationToggle);
        setFilterText("");
      }
    };

    return (
      <FilterComponent
        onFilter={(e) => setFilterText(e.target.value)}
        onClear={handleClear}
        filterText={filterText}
      />
    );
  }, [filterText, resetPaginationToggle]);

  return (
    <>
      <DeleteModal
        open={open}
        handleClose={handleClose}
        id={id}
        handleDelete={handleDelete}
      />
      <div className="add__btn">
        <Link to="/admin-panel/data/paths/add">
          <AddIcon />
          <h2>Add</h2>
        </Link>
      </div>
      <DataTable
        title="Paths"
        width={100}
        columns={columns}
        data={filteredItems}
        pagination
        highlightOnHover
        defaultSortField="id"
        subHeader
        subHeaderComponent={subHeaderComponentMemo}
        paginationResetDefaultPage={resetPaginationToggle}
      />
    </>
  );
}

export default PathsList;
