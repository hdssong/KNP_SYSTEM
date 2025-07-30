import { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { TextField, Button, Typography, Card, CardContent } from "@mui/material";

const LoginWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: #f5f5f5;
`;
const Logo = styled.img`
  width: 350px;
  height: auto;
`;

const Divider = styled.div`
  width: 1px;
  height: 60%;
  background-color: #ccc;
  margin: 0 80px; /* 좌우 여백 */
`;

const LoginCard = styled(Card)`
  width: 400px;
  padding: 24px;
`;

const Login = () => {
  const navigate = useNavigate();

  const [id, setId] = useState("");
  const [pwd, setPwd] = useState("");
  const handleLogin = (e) => {
    e.preventDefault(); // 폼 제출시 새로고침되는 거 막음
    if (id.trim() && pwd.trim()) {
      navigate("/home");
    } else {
      alert("아이디와 비밀번호를 모두 입력해주세요ㅎㅎ");
    }
  };
  return (
    <LoginWrapper>
      <Logo src="src/assets/logo.png" alt="logo" />
      <Divider />
      <LoginCard>
        <CardContent>
          <Typography variant="h5" align="center" gutterBottom>
            로그인
          </Typography>
          <form onSubmit={handleLogin}>
            <TextField
              fullWidth
              label="아이디"
              variant="outlined"
              margin="normal"
              value={id}
              onChange={(e) => setId(e.target.value)}
            />
            <TextField
              fullWidth
              label="비밀번호"
              type="password"
              variant="outlined"
              margin="normal"
              value={pwd}
              onChange={(e) => setPwd(e.target.value)}
            />
            <Button
              type="submit"
              variant="contained"
              color="primary"
              fullWidth
              sx={{ mt: 2, height: "48px", fontSize: "18px" }}
            >
              로그인
            </Button>
          </form>
        </CardContent>
      </LoginCard>
    </LoginWrapper>
  );
};

export default Login;
