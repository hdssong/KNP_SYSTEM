package kr.go.knp_system.domain.knpmember.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 로그인
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "knp_member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KnpMember {
    
    @Id                 
    @Column(name = "em_idnum",nullable = false) 
    private String emIdNum; 

    @Column(name = "em_name",nullable = false) 
    private String emName; 

    @Column(name="em_password",nullable = false) 
    private String emPasswd;
    
}
  