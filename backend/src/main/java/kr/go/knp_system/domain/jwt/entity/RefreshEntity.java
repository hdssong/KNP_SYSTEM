package kr.go.knp_system.domain.jwt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.go.knp_system.domain.knpreport.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jwt_refresh_entity")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshEntity extends BaseTimeEntity{

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "em_idnum",nullable = false,length = 50)
    private String emIdNum;

    @Column(name = "refresh", nullable = false, length = 512)
    private String refresh;
}
