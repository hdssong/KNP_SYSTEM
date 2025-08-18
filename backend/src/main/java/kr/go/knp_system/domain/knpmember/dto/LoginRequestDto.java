package kr.go.knp_system.domain.knpmember.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDto{
    
    private String emIdNum; // 로그인 사번
    private String emPasswd;    // 비밀번호
}