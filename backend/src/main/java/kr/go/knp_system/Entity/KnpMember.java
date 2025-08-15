package kr.go.knp_system.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class KnpMember {
    
    @Id                 // 기본키로
    @Column(name = "em_idnum",nullable = false) 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private String emIdNum; 

    @Column(name = "em_name",nullable = false) 
    private String emName; 

    @Column(name="em_password",nullable = false) 
    private String emPasswd;
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name="org_code",referencedColumnName = "org_code")
    // private KnpOrg knpOrg;
    
}
  