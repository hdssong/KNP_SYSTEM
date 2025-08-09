// src/components/layout/Header.jsx
import styled from "styled-components";
import {
  AppBar,
  Toolbar,
  Typography,
  Box,
  IconButton,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  createTheme,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import NotificationsIcon from "@mui/icons-material/Notifications";
import AccountCircle from "@mui/icons-material/AccountCircle";
import useAuthStore from "../../store/authStore";
import logo from "../../assets/logo.png";
import { useState } from "react";

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

const StyledDialog = styled(Dialog)`
  .MuiPaper-root {
    border-radius: 12px;
    padding: 8px;
    min-width: 480px;
  }
`;

const StyledDialogTitle = styled(DialogTitle)`
  font-size: 20px;
  font-weight: 600;
  color: #333;
  padding-bottom: 8px;
`;

const StyledDialogContent = styled(DialogContent)`
  color: #666;
  font-size: 16px;
  padding: 8px 24px 20px;
`;

const CancelButton = styled(Button)`
  color: #666;
  font-weight: 500;

  &:hover {
    background-color: rgba(0, 0, 0, 0.04);
  }
`;

const LogoutButton = styled(Button)`
  background-color: ${({ theme }) => theme.palette.primary.main};
  color: white;
  font-weight: 500;

  &:hover {
    background-color: ${({ theme }) => theme.palette.primary.dark};
  }
`;

function Header() {
  const navigate = useNavigate();
  const { isLoggedIn, logout } = useAuthStore();
  const [showLogoutDialog, setShowLogoutDialog] = useState(false);

  const handleLogoClick = () => {
    navigate(isLoggedIn ? "/" : "/login");
  };

  const handleAccountClick = () => {
    setShowLogoutDialog(true);
  };

  const handleCloseDialog = () => {
    setShowLogoutDialog(false);
  };

  const handleConfirmLogout = () => {
    logout();
    setShowLogoutDialog(false);
    navigate("/login");
  };

  return (
    <CustomAppBar position="fixed" elevation={0}>
      <CustomToolbar>
        <Box
          onClick={handleLogoClick}
          sx={{ display: "flex", alignItems: "center", cursor: "pointer" }}
        >
          <Logo src={logo} alt="logo" />
          <Typography variant="h6" sx={{ color: "black" }}>
            사건 Issue
          </Typography>
        </Box>
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
                onClick={handleAccountClick}
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
      <StyledDialog
        open={showLogoutDialog}
        onClose={handleCloseDialog}
        aria-labelledby="logout-dialog-title"
      >
        <StyledDialogTitle id="logout-dialog-title">알림</StyledDialogTitle>
        <StyledDialogContent>로그아웃 하시겠습니까?</StyledDialogContent>
        <DialogActions sx={{ padding: "8px 24px 16px", gap: 1 }}>
          <CancelButton onClick={handleCloseDialog}>취소</CancelButton>
          <LogoutButton onClick={handleConfirmLogout} variant="contained">
            로그아웃
          </LogoutButton>
        </DialogActions>
      </StyledDialog>
    </CustomAppBar>
  );
}

export default Header;
