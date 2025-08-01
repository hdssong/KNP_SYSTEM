import Header from "./Header";
import Footer from "./Footer";
import { Box } from "@mui/material";
import GlobalStyle from "../../styles/GlobalStyle";

function Layout({ children }) {
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
