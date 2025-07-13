import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const navigate = useNavigate();

  const [id, setId] = useState("");
  const [pwd, setPwd] = useState("");
  const hadleLogin = (e) => {
    e.preventDefault(); // 폼 제출시 새로고침되는 거 막음
    if (id.trim() && pwd.trim()) {
      navigate("/home");
    } else {
      alert("아이디와 비밀번호를 모두 입력해주세요ㅎㅎ");
    }
  };
  return (
    <div>
      <form onSubmit={hadleLogin}>
        <label>아이디</label>
        <input type="text" value={id} onChange={(e) => setId(e.target.value)} />
        <label>비밀번호</label>
        <input type="text" value={pwd} onChange={(e) => setPwd(e.target.value)} />
        <button>로그인</button>
      </form>
    </div>
  );
};

export default Login;
