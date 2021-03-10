import React, { useEffect, useState } from "react";
import Cookies from "js-cookie";
import axios from "axios";
import DataTable from "react-data-table-component";
import { BASE_URL } from "../../../utils/URL";
import DeleteIcon from "@material-ui/icons/Delete";

axios.interceptors.request.use((config) => {
  config.headers.authorization = "Bearer " + Cookies.get("token");
  return config;
});

function PathsList() {
  const [adminPaths, setAdminPaths] = useState([]);
  const data = adminPaths;

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
      console.log("Delete was successful");
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
      width: "250px",
      selector: "description",
      sortable: true,
      center: true,

      cell: (row) => <div>{row.description.substring(0, 35)}...</div>,
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
      cell: (row) => <div>{row.featured.toString()}</div>,
    },
    {
      name: "Published",
      width: "70px",
      selector: "published",
      sortable: true,
      center: true,
      cell: (row) => <div>{row.published.toString()}</div>,
    },
    {
      name: "Del",
      width: "50px",
      selector: "id",
      center: true,
      cell: (row) => (
        <button
          type="button"
          title="Delete"
          onClick={() => handleDelete(row.id)}
        >
          <DeleteIcon />
        </button>
      ),
    },
  ];
  return (
    <>
      <div style={{ margin: "0 20px" }}>
        <DataTable
          title="Paths"
          width={100}
          columns={columns}
          data={data}
          pagination
          highlightOnHover
          defaultSortField="id"
          defaultSortAsc={false}
        />
      </div>
    </>
  );
}

export default PathsList;
