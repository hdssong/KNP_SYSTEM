package kr.go.knp_system.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    
    @Id
    private String username;
    private String passwd;
    private String department;
    private LocalDateTime lastLogin;

}
