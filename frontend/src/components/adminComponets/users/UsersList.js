import React, { forwardRef, useEffect, useState } from "react";
import Cookies from "js-cookie";
import axios from "axios";
import MaterialTable from "material-table";
import AddBox from "@material-ui/icons/AddBox";
import ArrowDownward from "@material-ui/icons/ArrowDownward";
import Check from "@material-ui/icons/Check";
import ChevronLeft from "@material-ui/icons/ChevronLeft";
import ChevronRight from "@material-ui/icons/ChevronRight";
import Clear from "@material-ui/icons/Clear";
import DeleteOutline from "@material-ui/icons/DeleteOutline";
import Edit from "@material-ui/icons/Edit";
import FilterList from "@material-ui/icons/FilterList";
import FirstPage from "@material-ui/icons/FirstPage";
import LastPage from "@material-ui/icons/LastPage";
import Remove from "@material-ui/icons/Remove";
import SaveAlt from "@material-ui/icons/SaveAlt";
import Search from "@material-ui/icons/Search";
import ViewColumn from "@material-ui/icons/ViewColumn";

const tableIcons = {
  Add: forwardRef((props, ref) => <AddBox {...props} ref={ref} />),
  Check: forwardRef((props, ref) => <Check {...props} ref={ref} />),
  Clear: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
  Delete: forwardRef((props, ref) => <DeleteOutline {...props} ref={ref} />),
  DetailPanel: forwardRef((props, ref) => (
    <ChevronRight {...props} ref={ref} />
  )),
  Edit: forwardRef((props, ref) => <Edit {...props} ref={ref} />),
  Export: forwardRef((props, ref) => <SaveAlt {...props} ref={ref} />),
  Filter: forwardRef((props, ref) => <FilterList {...props} ref={ref} />),
  FirstPage: forwardRef((props, ref) => <FirstPage {...props} ref={ref} />),
  LastPage: forwardRef((props, ref) => <LastPage {...props} ref={ref} />),
  NextPage: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
  PreviousPage: forwardRef((props, ref) => (
    <ChevronLeft {...props} ref={ref} />
  )),
  ResetSearch: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
  Search: forwardRef((props, ref) => <Search {...props} ref={ref} />),
  SortArrow: forwardRef((props, ref) => <ArrowDownward {...props} ref={ref} />),
  ThirdStateCheck: forwardRef((props, ref) => <Remove {...props} ref={ref} />),
  ViewColumn: forwardRef((props, ref) => <ViewColumn {...props} ref={ref} />),
};

axios.interceptors.request.use((config) => {
  config.headers.authorization = "Bearer " + Cookies.get("token");
  return config;
});

function UsersList() {
  const [users, setUsers] = useState([]);

  const [state] = React.useState({
    columns: [
      {
        title: "Id",
        field: "id",
        editable: "never",
      },
      {
        title: "Username",
        field: "username",
      },
      {
        title: "Email",
        field: "email",
      },
      {
        title: "Roles",
        field: "roles",
        lookup: {
          // ROLE_ADMIN: "Admin",
          ROLE_USER: "User",
          ROLE_MODERATOR: "Moderator",
        },
      },
    ],
  });

  //Get all users
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/admin/all")
      .then((response) => {
        setUsers(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  // filters all iteams by itemId
  const deleteFromArray = (itemId) => {
    setUsers(users.filter(({ id }) => id !== itemId));
  };

  const deleteUser = (id) => {
    axios
      .delete(`http://localhost:8080/api/admin/delete/${id}`)
      .then((response) => {
        console.log(`${id} id record deleted`);
        console.log(response.status);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  //Update user
  const updateUser = (id, updatedUser) => {
    axios
      .post(`http://localhost:8080/api/admin/user/${id}`, updatedUser)
      .then((response) => {
        console.log(`${id} id record updated`);
        console.log(response.status);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <MaterialTable
        title="Users Data"
        icons={tableIcons}
        columnResizable={true}
        columns={state.columns}
        data={users}
        editable={{
          isEditHidden: (rowData) => rowData.roles === "ROLE_ADMIN",
          isDeleteHidden: (rowData) => rowData.roles === "ROLE_ADMIN",
          onRowUpdate: (newData, oldData) =>
            new Promise((resolve) => {
              const oldIndex = users.indexOf(oldData);
              const arrayId = oldData.id;
              setTimeout(() => {
                resolve();
                if (oldData) {
                  console.log(`Old data index ${oldIndex}`);
                  console.log(arrayId);
                  setUsers((prevState) => {
                    prevState = prevState.filter(({ id }) => id !== arrayId);
                    return [...prevState, newData];
                  });
                  updateUser(arrayId, newData);
                }
              }, 600);
            }),
          onRowDelete: (oldData) =>
            new Promise((resolve) => {
              setTimeout(() => {
                resolve();
                console.log(oldData);
                const arrayId = oldData.id;
                console.log(arrayId);
                deleteUser(arrayId);
                deleteFromArray(arrayId);
              }, 600);
            }),
        }}
      />
    </div>
  );
}

export default UsersList;
