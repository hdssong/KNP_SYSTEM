import { createGlobalStyle } from "styled-components";

const GlobalStyle = createGlobalStyle`
  * {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
  }

#root {
    height: 100%;
}
  
  body {
    font-family: ${({ theme }) => theme.typography.fontFamily};
    background-color: #f5f5f5;
  }
`;

export default GlobalStyle;
