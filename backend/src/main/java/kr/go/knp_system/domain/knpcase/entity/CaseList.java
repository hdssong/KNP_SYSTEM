package kr.go.knp_system.domain.knpcase.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.go.knp_system.domain.knpreport.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * 전체 사건리스트
 */

@Entity
@Table(name = "case_info")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CaseList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "case_idnum", length = 50)
    private String caseIdnum; // 사건번호 (승인 시 생성)

    @Column(name = "report_id", nullable = false, length = 50)
    private String reportId; // 접수 번호 (참조)

    @Column(name = "report_type", length = 50)
    private String reportType; // 접수단서 (고소,타인신고,피해자신고)

    @Column(name = "case_receive_date", nullable = false)
    private LocalDateTime caseReceiveDate; // 사건 접수 일자

    @Column(name = "case_end_date")
    private LocalDateTime caseEndDate; // 사건종결일자

    @Column(name = "occur_date")
    private LocalDateTime occurDate; // 사건발생일자

    @Column(name = "case_charge", length = 100)
    private String caseCharge; // 죄명

    @Column(name = "case_data", length = 1000)
    private String caseData; // 사건개요

    @Column(name = "ongoing_date")
    private int ongoingDate; // 사건 경과 일수 (계산용)

    @Enumerated(EnumType.STRING)
    @Column(name = "case_status", nullable = false)
    private CaseStatus caseStatus; // 사건 상태 (한글 ENUM)

    @Column(name = "suspect", length = 100)
    private String suspect; // 피의자

    @Column(name = "victim", length = 100)
    private String victim; // 피해자

    @Column(name = "em_name", length = 50)
    private String emName; // 사건담당형사

    @Column(name = "evidence_file", length = 255)
    private String evidenceFile; // 증거자료 (파일 경로)

    @Column(name = "closure_idnum", length = 50)
    private String closureIdnum; // 종결번호

    @Builder
    public CaseList(String caseIdNum, String reportId, String reportType, LocalDateTime caseReceiveDate,
            LocalDateTime caseEndDate, LocalDateTime occurDate, String caseCharge, String caseData,
            int ongoingDate, CaseStatus caseStatus,String suspect, String victim, String emName, String closureIdnum) {
        this.caseIdnum = caseIdNum;
        this.reportId = reportId;
        this.reportType = reportType;
        this.caseReceiveDate = caseReceiveDate;
        this.caseEndDate = caseEndDate;
        this.occurDate = occurDate;
        this.caseCharge = caseCharge;
        this.caseData = caseData;
        this.ongoingDate = ongoingDate;
        this.caseStatus = caseStatus;
        this.suspect = suspect;
        this.victim = victim;
        this.emName = emName;
        this.closureIdnum = closureIdnum;
    }
}
