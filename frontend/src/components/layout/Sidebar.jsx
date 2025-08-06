// src/components/layout/Sidebar.jsx
import styled from "styled-components";
import { Drawer, List, ListItem, ListItemIcon, ListItemText } from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
import LogoutIcon from "@mui/icons-material/Logout";
import CreateIcon from "@mui/icons-material/Create";
import FormatListBulletedIcon from "@mui/icons-material/FormatListBulleted";
import { Link } from "react-router-dom";

const drawerWidth = 240;

const CustomDrawer = styled(Drawer)`
  & .MuiDrawer-paper {
    width: ${drawerWidth}px;
    box-sizing: border-box;
    background-color: #ffffff;
    border-right: 1px solid #e0e0e0;
    padding-left: 15px;
    padding-right: 15px;
    padding-top: 30px;
    position: fixed;
    top: 64px;
    height: calc(100vh - 64px);
  }
`;

function Sidebar() {
  return (
    <CustomDrawer variant="permanent" anchor="left">
      <List sx={{ display: "flex", flexDirection: "column", gap: 3 }}>
        <ListItem button component={Link} to="/dashboard">
          <ListItemIcon>
            <HomeIcon color="secondary" />
          </ListItemIcon>
          <ListItemText primary="대시보드" sx={{ color: "#6c757d" }} />
        </ListItem>
        {/* 클릭 시 이동 경로 수정 필요 */}
        <ListItem button component={Link} to="/dashboard">
          <ListItemIcon>
            <CreateIcon color="secondary" />
          </ListItemIcon>
          <ListItemText primary="조서 작성" sx={{ color: "#6c757d" }} />
          {/* TODO 로그인된 유저가 순경 또는 형사인지에 따라 다른 조서 작성 폼을 보여줘야 함. */}
        </ListItem>
        <ListItem button component={Link} to="/dashboard">
          <ListItemIcon>
            <FormatListBulletedIcon color="secondary" />
          </ListItemIcon>
          <ListItemText primary="사건 페이지" sx={{ color: "#6c757d" }} />
        </ListItem>
        <ListItem button component={Link} to="/logout">
          <ListItemIcon>
            <LogoutIcon color="secondary" />
          </ListItemIcon>
          <ListItemText primary="로그아웃" sx={{ color: "#6c757d" }} />
        </ListItem>
      </List>
    </CustomDrawer>
  );
}

export default Sidebar;
