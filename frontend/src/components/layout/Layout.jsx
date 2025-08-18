import Header from "../common/Header";
import Footer from "../common/Footer";
import { Box } from "@mui/material";
import GlobalStyle from "../../styles/GlobalStyle";
import Sidebar from "../common/Sidebar";
import useAuthStore from "../../store/authStore";

const drawerWidth = 240;
const headerHeight = 64;
const footerHeight = 73;

function Layout({ children }) {
  const isLoggedIn = useAuthStore((state) => state.isLoggedIn);
  return (
    <>
      <GlobalStyle />
      <Box sx={{ minHeight: "100vh", position: "relative" }}>
        {isLoggedIn && <Sidebar />}
        {/* 사이드바는 로그인시에만 */}

        {/* 콘텐츠 래퍼: 사이드바 있을 때만 왼쪽 마진으로 밀기 */}
        <Box
          sx={{
            ml: isLoggedIn ? `${drawerWidth}px` : 0, // ✅ 로그인만 적용
            display: "flex",
            flexDirection: "column",
            minHeight: "100vh",
          }}
        >
          <Box sx={{ height: `${headerHeight}px`, flexShrink: 0 }}>
            <Header />
          </Box>

          <Box
            component="main"
            sx={{
              flex: 1,
              px: 3,
              minHeight: `calc(100vh - ${headerHeight + footerHeight}px)`,
              display: "flex",
              justifyContent: "center",
              // ✅ 로그인: 위에서부터 시작 / 로그아웃: 자식이 세로로 꽉 차게
              alignItems: isLoggedIn ? "center" : "stretch",
            }}
          >
            {/* 공통 내부 컨테이너 */}
            <Box
              sx={{
                width: "100%",
                maxWidth: isLoggedIn ? 1200 : "100%",
                py: isLoggedIn ? 3 : 0,
              }}
            >
              {children}
            </Box>
          </Box>

          <Box sx={{ height: `${footerHeight}px`, flexShrink: 0 }}>
            <Footer />
          </Box>
        </Box>
      </Box>
    </>
  );
}

export default Layout;
