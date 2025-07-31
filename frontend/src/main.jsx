import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import { BrowserRouter } from "react-router-dom";

//mui 관련 import => mui 컴포넌트와 styled components 컴포넌트가 동일한 테마 공유가 가능해짐
import { ThemeProvider as MuiThemeProvider, createTheme } from "@mui/material/styles";
import { ThemeProvider as StyledThemeProvider } from "styled-components";
import theme from "./styles/theme.js";
import GlobalStyle from "./styles/GlobalStyle.js";

createRoot(document.getElementById("root")).render(
  <MuiThemeProvider theme={theme}>
    <StyledThemeProvider theme={theme}>
      <GlobalStyle />
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </StyledThemeProvider>
  </MuiThemeProvider>
);
