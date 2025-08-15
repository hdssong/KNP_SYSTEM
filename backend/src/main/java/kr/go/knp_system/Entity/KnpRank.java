package kr.go.knp_system.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "knp_rank")
@NoArgsConstructor
public class KnpRank {
    
    @Id
    @Column(name = "rank_id",nullable = false)   //직급코드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String rankId;

    @Column(name = "rank_name",nullable = false)    // 직급명
    private String rank_name;


}
