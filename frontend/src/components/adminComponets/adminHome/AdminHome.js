import React from "react";
import AdminHomeCard from "./AdminHomeCard";
import "./AdminHome.css";
import PanoramaOutlinedIcon from "@material-ui/icons/PanoramaOutlined";
import PeopleAltOutlinedIcon from "@material-ui/icons/PeopleAltOutlined";
import CommentOutlinedIcon from "@material-ui/icons/CommentOutlined";
import DescriptionOutlinedIcon from "@material-ui/icons/DescriptionOutlined";

function AdminHome() {
  const namesOfItems = [
    {
      id: 1,
      icon: <PanoramaOutlinedIcon />,
      title: "Paths",
      url: "/admin-panel/data/paths",
      desc: "List of all the paths. Edit, delete and more.",
    },
    {
      id: 2,
      icon: <PeopleAltOutlinedIcon />,
      title: "Users",
      url: "/admin-panel/data/users",
      desc: "List of all the users. Edit, ban and more.",
    },
    {
      id: 3,
      icon: <CommentOutlinedIcon />,
      title: "Articles",
      url: "/admin-panel/data/articles",
      desc: "List of all the articles. Edit, delete and more.",
    },
    {
      id: 4,
      icon: <DescriptionOutlinedIcon />,
      title: "Comments",
      url: "/admin-panel/data/comments",
      desc: "List of all the comments. Delete and more.",
    },
  ];

  return (
    <div className="adminHome">
      {namesOfItems.map((item) => {
        return <AdminHomeCard key={item.id} item={item} />;
      })}
    </div>
  );
}

export default AdminHome;
