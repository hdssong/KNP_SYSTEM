package kr.go.knp_system.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDto {

    private String emIdNum;
    private String emPasswd;

}