import { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { TextField, Button, Typography, Card, Box, CardContent } from "@mui/material";
import useAuthStore from "../../store/authStore";
import logo from "../../assets/logo.png";

const LoginWrapper = styled.div`
  display: flex;
  width: 100%;
  height: 100%;
`;
const Logo = styled.img`
  width: 300px;
  height: auto;
`;

const LeftSection = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  user-select: none;
  pointer-events: none;
`;

const RightSection = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: white;
`;

const LoginCard = styled(Card)`
  width: 400px;
  padding: 24px;
  box-shadow: none;
`;

const Login = () => {
  const navigate = useNavigate();
  const loginUser = useAuthStore((state) => state.loginUser); //현재 로그인된 유저

  const [emIdNum, setEmIdNum] = useState("");
  const [emPasswd, setEmPasswd] = useState("");
  const handleLogin = async (e) => {
    e.preventDefault(); // 폼 제출시 새로고침되는 거 막음
    if (!emIdNum.trim() || !emPasswd.trim()) {
      return alert("아이디와 비밀번호를 모두 입력해주세요!");
    }
    console.log("보낼 로그인 데이터:", {
      emIdNum,
      emPasswd,
    });
    try {
      const data = await loginUser({ emIdNum, emPasswd });
      navigate("/dashboard");
    } catch (error) {
      console.log("로그인 실패: ", error);
      alert(error.response?.data?.message || "로그인에 실패했습니다.");
    }
  };
  return (
    <LoginWrapper>
      <LeftSection>
        <Logo src={logo} alt="logo" />
      </LeftSection>
      <RightSection>
        <LoginCard>
          <CardContent>
            <Typography variant="h4" align="center" gutterBottom sx={{ mb: 5, fontWeight: 600 }}>
              로그인
            </Typography>
            <form onSubmit={handleLogin}>
              <Box sx={{ display: "flex", flexDirection: "column", gap: 5 }}>
                <TextField
                  fullWidth
                  label="아이디"
                  variant="outlined"
                  value={emIdNum}
                  onChange={(e) => setEmIdNum(e.target.value)}
                />
                <TextField
                  fullWidth
                  label="비밀번호"
                  type="password"
                  variant="outlined"
                  value={emPasswd}
                  onChange={(e) => setEmPasswd(e.target.value)}
                />
                <Button
                  type="submit"
                  variant="contained"
                  color="primary"
                  fullWidth
                  sx={{ height: "48px", fontSize: "18px" }}
                >
                  로그인
                </Button>
              </Box>
            </form>
          </CardContent>
        </LoginCard>
      </RightSection>
    </LoginWrapper>
  );
};

export default Login;
