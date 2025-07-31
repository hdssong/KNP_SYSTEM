// src/components/layout/Header.jsx
import styled from "styled-components";
import {
  AppBar,
  Toolbar,
  Typography,
  Box,
  Button,
  Divider,
  IconButton,
  Avatar,
} from "@mui/material";
import { Link } from "react-router-dom";
import NotificationsIcon from "@mui/icons-material/Notifications";
import AccountCircle from "@mui/icons-material/AccountCircle";

const Logo = styled.img`
  height: 80px;
  width: 50px;
  margin-right: 8px;
`;

const Spacer = styled.div`
  flex-grow: 1; /* 왼쪽 로고와 오른쪽 메뉴 사이를 밀어냄 */
`;

const CustomToolbar = styled(Toolbar)`
  padding-left: 50px;
  padding-right: 50px;
`;
function Header() {
  return (
    <AppBar position="static" color="background" elevation={0}>
      <CustomToolbar>
        <Link to="/home">
          <Logo src="/src/assets/logo.png" alt="logo" />
        </Link>
        <Typography
          variant="h5"
          component={Link}
          to="/"
          style={{ textDecoration: "none", color: "black" }}
        >
          사건 Issue
        </Typography>
        <Spacer />
        <Box sx={{ display: "flex", alignItems: "center", gap: 2 }}>
          <IconButton color="background">
            <NotificationsIcon fontSize="large" />
          </IconButton>
          <IconButton color="background">
            <AccountCircle fontSize="large" />
          </IconButton>
        </Box>
      </CustomToolbar>
      <Divider />
    </AppBar>
  );
}

export default Header;
