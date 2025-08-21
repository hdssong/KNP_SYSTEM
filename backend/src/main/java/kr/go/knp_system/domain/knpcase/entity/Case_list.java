package kr.go.knp_system.domain.knpcase.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 
 * 전체 사건리스트
 */

@Entity
@Table(name = "case_info")
public class Case_list {
    
    @Id
    @Column(name = "case_idnum", length = 50)
    private String caseIdnum; // 사건번호 (승인 시 생성)

    @Column(name = "report_num", nullable = false, length = 50)
    private String reportNum;   // 신고 번호 (참조)

    @Column(name = "report_type", length = 50)
    private String reportType; // 접수단서

    @Column(name = "case_receive_date", nullable = false)
    private LocalDateTime caseReceiveDate;  // 사건 접수 일자

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
    @Column(name = "case_status", nullable = false,
            columnDefinition = "ENUM('대기','진행중','승인','반려','종결')")
    private CaseStatus caseStatus;  // 사건 상태 (한글 ENUM)

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
}
