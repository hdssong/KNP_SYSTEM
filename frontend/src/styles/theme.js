import { createTheme } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    primary: { main: "#0056b3", dark: "#004494" }, // primary 색상
    secondary: { main: "#6c757d" }, // secondary 색상
    background: { default: "#f5f5f5" }, // 배경색
  },

  typography: {
    fontFamily: '"Asta Sans", "Noto Sans KR", sans-serif',
    h5: { fontWeight: 600 },
  },
});

export default theme;
