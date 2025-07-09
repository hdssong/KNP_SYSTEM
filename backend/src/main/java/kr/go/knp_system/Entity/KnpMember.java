package kr.go.knp_system.Entity;

import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 개인정보 클래스
 */

@Entity // 이 클래스가 데이터베이스 테이블과 매핑됨을 의미
@Table(name = "knp_member") // 테이블 이름을 명시적으로 지정할 수 있음
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KnpMember {
    
    @Id // 기본키로 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임
    private String  memberId;      // 사번

    @Column(name="username",nullable = false)
    private String userName;    

    private String userPassword;

    // @Column(name="email", unique = true)
    // private String email;

    // @Column(name="create_at")
    // private LocalDateTime createAt;

    // @PrePersist
    // protected void onCreate(){
    //     this.createAt = LocalDateTime.now();
    // }
    
}
