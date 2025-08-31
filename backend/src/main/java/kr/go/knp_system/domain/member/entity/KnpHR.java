package kr.go.knp_system.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "knp_hr")
@Getter
@NoArgsConstructor
public class KnpHR {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hr_id")
    private Long hrId;

    @Column(name = "em_name", nullable = false, length = 30)
    private String emName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "em_idnum", referencedColumnName = "em_idnum", nullable = false)
    private KnpMember knpMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_code", referencedColumnName = "org_code", nullable = false)
    private KnpOrg knpOrg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id", referencedColumnName = "rank_id", nullable = false)
    private KnpRank knpRank;
}