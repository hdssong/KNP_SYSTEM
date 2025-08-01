import { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { TextField, Button, Typography, Card, Box, CardContent } from "@mui/material";
import useAuthStore from "../../store/authStore";

const LoginWrapper = styled.div`
  display: flex;
  width: 100%;
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
  const login = useAuthStore((state) => state.login);

  const [id, setId] = useState("");
  const [pwd, setPwd] = useState("");
  const handleLogin = (e) => {
    e.preventDefault(); // 폼 제출시 새로고침되는 거 막음
    if (id.trim() && pwd.trim()) {
      login({ id }); // 로그인시 상태 변경
      navigate("/home");
    } else {
      alert("아이디와 비밀번호를 모두 입력해주세요!");
    }
  };
  return (
    <LoginWrapper>
      <LeftSection>
        <Logo src="/logo.png" alt="logo" />
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
                  value={id}
                  onChange={(e) => setId(e.target.value)}
                />
                <TextField
                  fullWidth
                  label="비밀번호"
                  type="password"
                  variant="outlined"
                  value={pwd}
                  onChange={(e) => setPwd(e.target.value)}
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
