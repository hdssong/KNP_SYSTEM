package kr.go.knp_system.Entity;

import java.security.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 로그인
 */

@Entity // 이 클래스가 데이터베이스 테이블과 매핑됨을 의미
@Table(name = "knp_member") // 테이블 이름을 명시적으로 지정할 수 있음
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KnpMember {
    
    @Id                 // 기본키로
    @Column(name = "em_idnum")  // 사번 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임
    private String emIdNum; 

    @Column(name="em_name",nullable = false)    //이름
    private String emName;    

    @Column(name="em_user_id",nullable = false)  // 아이디
    private String emUserId;
    
    @Column(name="em_password",nullable = false)   //비밀번호
    private String emPasswd;

    @Column(name="email", unique = true)    //이메일
    private String email;

    @Column(name="role", unique = true)    //계급
    private String role;
}
