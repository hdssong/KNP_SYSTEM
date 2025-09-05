package kr.go.knp_system.domain.knpcase.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.go.knp_system.domain.member.entity.KnpMember;
import lombok.Builder;

@Entity
@Table(name = "case_assignment")
public class CaseAssignment {
    
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id",nullable = false)
    private CaseList caseList;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "em_idnum",nullable = false)
    private KnpMember assignMember;     //사건 담당

    @Column(name = "is_active")
    private boolean active;     // 담당자 이력용

    @Column(name = "case_assigned_date")
    private LocalDateTime caseAssignedDateTime;     // 사건배정일자

    @Builder CaseAssignment(CaseList caseList,KnpMember assignMember, boolean active,LocalDateTime caseAssignedDateTime){
        this.caseList = caseList;
        this.assignMember = assignMember;
        this.active = active;
        this.caseAssignedDateTime = caseAssignedDateTime;
    }
}
