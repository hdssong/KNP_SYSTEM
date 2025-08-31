package kr.go.knp_system.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

    private String token;
    private String refreshToken;
    private String emIdNum;   // 사번
    private String emName;    // 이름
    private String org_full_path_name;   // 소속
    private String rankName;  // 직급

    public LoginResponseDto(String emIdNum,String emName,String org_full_path_name,String rankName) {
        this.emIdNum = emIdNum;
        this.emName = emName;
        this.org_full_path_name = org_full_path_name;
        this.rankName = rankName;
    }
}