import React from "react";
import ArrowDropUpIcon from "@material-ui/icons/ArrowDropUp";
import ArrowDropDownIcon from "@material-ui/icons/ArrowDropDown";
import FiberManualRecordOutlinedIcon from "@material-ui/icons/FiberManualRecordOutlined";
import SupervisorAccountIcon from "@material-ui/icons/SupervisorAccount";
import DashboardIcon from "@material-ui/icons/Dashboard";
import DataUsageIcon from "@material-ui/icons/DataUsage";
import GroupIcon from "@material-ui/icons/Group";
import MessageIcon from "@material-ui/icons/Message";
import HelpIcon from "@material-ui/icons/Help";

export const SidebarData = [
  {
    title: "Admin Home",
    path: "/admin-panel/admin-home",
    icon: <SupervisorAccountIcon />,
  },
  {
    title: "Dashboard",
    icon: <DashboardIcon />,
    path: "#",
    iconClosed: <ArrowDropDownIcon />,
    iconOpened: <ArrowDropUpIcon />,
    subNav: [
      {
        title: "Statistics",
        path: "/admin-panel/dashboard/statistics",
        icon: <FiberManualRecordOutlinedIcon />,
      },
      {
        title: "Settings",
        path: "/admin-panel/dashboard/settings",
        icon: <FiberManualRecordOutlinedIcon />,
      },
    ],
  },
  {
    title: "Data",
    path: "#",
    icon: <DataUsageIcon />,
    iconClosed: <ArrowDropDownIcon />,
    iconOpened: <ArrowDropUpIcon />,
    subNav: [
      {
        title: "Users",
        path: "/admin-panel/data/users",
        icon: <FiberManualRecordOutlinedIcon />,
        cName: "sub-nav",
      },
      {
        title: "Paths",
        path: "/admin-panel/data/paths",
        icon: <FiberManualRecordOutlinedIcon />,
        cName: "sub-nav",
      },
      {
        title: "Comments",
        path: "/admin-panel/data/comments",
        icon: <FiberManualRecordOutlinedIcon />,
      },
    ],
  },

  {
    title: "Team",
    path: "/admin-panel/team",
    icon: <GroupIcon />,
  },
  {
    title: "Messages",
    icon: <MessageIcon />,
    path: "#",
    iconClosed: <ArrowDropDownIcon />,
    iconOpened: <ArrowDropUpIcon />,
    subNav: [
      {
        title: "Message 1",
        path: "/admin-panel/messages/message1",
        icon: <FiberManualRecordOutlinedIcon />,
      },
      {
        title: "Message 2",
        path: "/admin-panel/messages/message2",
        icon: <FiberManualRecordOutlinedIcon />,
      },
    ],
  },
  {
    title: "Support",
    path: "/admin-panel/support",
    icon: <HelpIcon />,
  },
];
