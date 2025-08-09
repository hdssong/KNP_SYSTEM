import Header from "./Header";
import Footer from "./Footer";
import { Box } from "@mui/material";
import GlobalStyle from "../../styles/GlobalStyle";
import Sidebar from "./Sidebar";
import useAuthStore from "../../store/authStore";

function Layout({ children }) {
  const isLoggedIn = useAuthStore((state) => state.isLoggedIn);
  return (
    <>
      <GlobalStyle />
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          minHeight: "100vh",
        }}
      >
        {isLoggedIn && <Sidebar />}
        <Header />
        <Box sx={{ flex: 1, display: "flex", alignItems: "stretch", justifyContent: "center" }}>
          {children}
        </Box>
        <Footer />
      </Box>
    </>
  );
}

export default Layout;
