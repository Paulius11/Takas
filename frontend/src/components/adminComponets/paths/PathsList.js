import React, { useEffect, useState } from "react";
import Cookies from "js-cookie";
import axios from "axios";
import DataTable from "react-data-table-component";
import { BASE_URL } from "../../../utils/URL";
import DeleteIcon from "@material-ui/icons/Delete";
import { Link } from "react-router-dom";
import AddIcon from "@material-ui/icons/Add";
import "./AddPath.css";
import CheckIcon from "@material-ui/icons/Check";
import ClearIcon from "@material-ui/icons/Clear";
import DeleteModal from "./DeleteModal";
import EditIcon from "@material-ui/icons/Edit";
import MoreVertIcon from "@material-ui/icons/MoreVert";
import MoreModal from "./MoreModal";

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
  //setting values for delete modal
  const [passToModal, setPassToModal] = useState({ id: 1, title: "" });
  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => {
    setIsModalOpen(true);
  };
  const closeModal = () => {
    setIsModalOpen(false);
  };

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
      width: "auto",
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
      width: "60px",
      selector: "rating",
      sortable: true,
      center: true,
      cell: (row) => <div>{row.rating}</div>,
    },
    {
      name: "Region",
      selector: "region",
      width: "110px",
      sortable: true,
      center: true,
      cell: (row) => (
        <div style={{ textTransform: "capitalize" }}>
          {row.region.toLowerCase()}
        </div>
      ),
    },
    {
      name: "Featured",
      width: "60px",
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
      width: "60px",
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
    {
      name: "Actions",
      width: "100px",
      selector: "id",
      center: true,
      cell: (row) => (
        <>
          <DeleteIcon
            title="Delete"
            onClick={() => {
              handleOpen();
              setPassToModal({ id: row.id, title: row.title });
            }}
          />
          <Link
            className="edit"
            to={"/admin-panel/data/paths/edit/" + row.id}
            title="Edit"
          >
            <EditIcon title="Edit" />
          </Link>
          <Link
            className="edit"
            onClick={() => {
              openModal();
              setPassToModal({
                id: row.id,
                title: row.title,
                description: row.description,
                region: row.region,
                length: row.length,
                image: row.image,
                featured: row.featured,
                published: row.published,
                rating: row.rating,
                difficulty: row.difficulty,
              });
            }}
            title="More"
            to="#"
          >
            <MoreVertIcon />
          </Link>
        </>
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
        handleDelete={handleDelete}
        passToModal={passToModal}
      />
      <MoreModal
        isModalOpen={isModalOpen}
        closeModal={closeModal}
        passToModal={passToModal}
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
        defaultSortAsc={false}
        subHeader
        subHeaderComponent={subHeaderComponentMemo}
        paginationResetDefaultPage={resetPaginationToggle}
      />
    </>
  );
}

export default PathsList;
