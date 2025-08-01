// src/components/layout/Sidebar.jsx
import styled from "styled-components";
import { Drawer, List, ListItem, ListItemIcon, ListItemText } from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
import BarChartIcon from "@mui/icons-material/BarChart";
import SettingsIcon from "@mui/icons-material/Settings";
import LogoutIcon from "@mui/icons-material/Logout";
import { Link } from "react-router-dom";

const drawerWidth = 240;

const CustomDrawer = styled(Drawer)`
  & .MuiDrawer-paper {
    width: ${drawerWidth}px;
    box-sizing: border-box;
    background-color: #fafafa;
    border-right: 1px solid #e0e0e0;
  }
`;

function Sidebar() {
  return (
    <CustomDrawer variant="permanent" anchor="left">
      <List>
        <ListItem button component={Link} to="/dashboard">
          <ListItemIcon>
            <HomeIcon color="primary" />
          </ListItemIcon>
          <ListItemText primary="대시보드" />
        </ListItem>

        <ListItem button component={Link} to="/reports">
          <ListItemIcon>
            <BarChartIcon color="primary" />
          </ListItemIcon>
          <ListItemText primary="리포트" />
        </ListItem>

        <ListItem button component={Link} to="/settings">
          <ListItemIcon>
            <SettingsIcon color="primary" />
          </ListItemIcon>
          <ListItemText primary="설정" />
        </ListItem>

        <ListItem button component={Link} to="/logout">
          <ListItemIcon>
            <LogoutIcon color="primary" />
          </ListItemIcon>
          <ListItemText primary="로그아웃" />
        </ListItem>
      </List>
    </CustomDrawer>
  );
}

export default Sidebar;
