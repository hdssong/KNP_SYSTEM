package kr.go.knp_system.DTO;

import lombok.Data;

// @Getter @Setter
@Data
public class LoginRequest {
    private String username;
    private String password;
}
