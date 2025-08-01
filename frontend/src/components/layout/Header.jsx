// src/components/layout/Header.jsx
import styled from "styled-components";
import { AppBar, Toolbar, Typography, Box, IconButton } from "@mui/material";
import { Link } from "react-router-dom";
import NotificationsIcon from "@mui/icons-material/Notifications";
import AccountCircle from "@mui/icons-material/AccountCircle";
import useAuthStore from "../../store/authStore";
import logo from "../../assets/logo.png";

const Logo = styled.img`
  height: 48px;
  width: auto;
  margin-right: 12px;
`;

const Spacer = styled.div`
  flex-grow: 1;
`;

const CustomAppBar = styled(AppBar)`
  background-color: #ffffff;
  color: #333;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.05);
  border-bottom: 1px solid #e0e0e0;
`;

const CustomToolbar = styled(Toolbar)`
  padding: 0 40px;
  min-height: 64px;
`;

function Header() {
  const isLoggedIn = useAuthStore((state) => state.isLoggedIn);
  return (
    <CustomAppBar position="sticky" elevation={0}>
      <CustomToolbar>
        <Link to="/home" style={{ display: "flex", alignItems: "center", textDecoration: "none" }}>
          <Logo src={logo} alt="logo" />
          <Typography variant="h6" sx={{ color: "black" }}>
            사건 Issue
          </Typography>
        </Link>
        <Spacer />
        <Box sx={{ display: "flex", alignItems: "center", gap: 1 }}>
          {isLoggedIn && (
            <>
              <IconButton
                sx={{
                  color: "#555",
                  "&:hover": { backgroundColor: "rgba(0,0,0,0.04)" },
                }}
              >
                <NotificationsIcon fontSize="medium" />
              </IconButton>
              <IconButton
                sx={{
                  color: "#555",
                  "&:hover": { backgroundColor: "rgba(0,0,0,0.04)" },
                }}
              >
                <AccountCircle fontSize="medium" />
              </IconButton>
            </>
          )}
        </Box>
      </CustomToolbar>
    </CustomAppBar>
  );
}

export default Header;
