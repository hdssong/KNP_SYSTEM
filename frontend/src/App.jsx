import { Route, Routes } from "react-router-dom";
import Login from "./pages/Auth/Login";
import Home from "./pages/Home";
import Layout from "./components/layout/Layout";

function App() {
  return (
    <Layout>
      <Routes>
        <Route path="/" element={<Login />} />{" "}
        {/* 나중에 path 수정 필요 - 로그인 여부에 따라 보여줄 화면 달라지게 */}
        <Route path="/home" element={<Home />} />
      </Routes>
    </Layout>
  );
}

export default App;
