package kr.go.knp_system.domain.knpmember.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "knp_hr",
       uniqueConstraints = {
         @UniqueConstraint(name="knp_hr_unique", columnNames={"em_idnum","org_code"})
       })
@Getter @Setter
@NoArgsConstructor
public class KnpHR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hr_id")
    private Long hrId;

    @Column(name = "em_idnum")
    private String emIdNum;

    @Column(name = "em_name", length = 100, nullable = false)
    private String emName;

    // 조직 FK : org_code(UNIQUE) → 다대일
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "org_code", referencedColumnName = "org_code", nullable = false)
    private KnpOrg knpOrg;

    // 직급 FK : rank_id → 다대일
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rank_id", referencedColumnName = "rank_id", nullable = false)
    private KnpRank knpRank;
}