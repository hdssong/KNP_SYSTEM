package kr.go.knp_system.RequestDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
// @AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto{
    
    private String emIdNum; // 로그인 사번
    private String emPasswd;    // 비밀번호
}