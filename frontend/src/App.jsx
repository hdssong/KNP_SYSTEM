import { Route, Routes } from "react-router-dom";
import Login from "./pages/Auth/Login";

import Layout from "./components/layout/Layout";
import { useEffect } from "react";
import useAuthStore from "./store/authStore";
import Main from "./pages/Main";
import DashBoard from "./pages/DashBoard";

function App() {
  // 앱 실행시 로그인 상태 가져오기
  const initFromStorage = useAuthStore((state) => state.initFromStorage);
  useEffect(() => {
    initFromStorage();
  }, [initFromStorage]);

  return (
    <Layout>
      <Routes>
        <Route path="/login" element={<Login />} />{" "}
        {/* 나중에 path 수정 필요 - 로그인 여부에 따라 보여줄 화면 달라지게 */}
        <Route path="/" element={<Login />} />
        <Route path="/dashboard" element={<DashBoard />} />
      </Routes>
    </Layout>
  );
}

export default App;
