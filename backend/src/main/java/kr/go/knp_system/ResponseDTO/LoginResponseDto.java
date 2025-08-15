package kr.go.knp_system.ResponseDTO;

import kr.go.knp_system.Entity.KnpMember;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

    private String emIdNum; // 사번
    private String emPassword; // 비밀번호

    //private String emUserId; // 로그인 아이디
    private String emName; // 이름
    
    public LoginResponseDto(KnpMember knpmember) {
        this.emIdNum = knpmember.getEmIdNum();
        this.emPassword = knpmember.getEmPasswd();
    }

}