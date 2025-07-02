package kr.go.knp_system.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserInfo {
    
    @Id
    private String username;

    private String name;
    private String email;
    private String password;  // LDAP 인증만 쓸 경우 저장 X or dummy

}
