package kr.go.knp_system.DTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginRequestDto {

    private String emIdNum; // 로그인 사번
    //private String emUserId;    // 로그인 아이디
    //private String emName;  // 이름
    private String password;    //


}