import { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const LoginWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
`;

const LoginForm = styled.form`
  width: 400px;
  padding: 32px;
  background-color: white;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
`;

const Label = styled.label`
  display: block;
  font-size: 14px;
  margin-bottom: 8px;
`;

const Input = styled.input`
  width: 80%;
  height: 48px;
  font-size: 16px;
  padding: 12px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 6px;
`;

const LoginButton = styled.button`
  width: 50%;
  height: 48px;
  font-size: 16px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 6px;
  &:hover {
    background-color: #0056b3;
  }
`;

const Login = () => {
  const navigate = useNavigate();

  const [id, setId] = useState("");
  const [pwd, setPwd] = useState("");
  const hadleLogin = (e) => {
    e.preventDefault(); // 폼 제출시 새로고침되는 거 막음
    if (id.trim() && pwd.trim()) {
      navigate("/home");
    } else {
      alert("아이디와 비밀번호를 모두 입력해주세요");
    }
  };
  return (
    <LoginWrapper>
      <LoginForm onSubmit={hadleLogin}>
        <Label>아이디</Label>
        <Input type="text" value={id} onChange={(e) => setId(e.target.value)} />
        <Label>비밀번호</Label>
        <Input type="text" value={pwd} onChange={(e) => setPwd(e.target.value)} />
        <LoginButton>로그인</LoginButton>
      </LoginForm>
    </LoginWrapper>
  );
};

export default Login;
