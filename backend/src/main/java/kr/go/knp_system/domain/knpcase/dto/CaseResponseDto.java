package kr.go.knp_system.domain.knpcase.dto;

import java.time.LocalDateTime;

import kr.go.knp_system.domain.knpcase.entity.CaseList;
import kr.go.knp_system.domain.knpcase.entity.CaseStatus;
import lombok.Getter;

@Getter
public class CaseResponseDto {
    
    private long id;
    private String caseIdnum; // 사건번호 (승인 시 생성)
    private String reportId;   // 접수 번호 (참조)
    private String reportType; // 접수단서 (고소,타인신고,피해자신고)
    private LocalDateTime caseReceiveDate;  // 사건 접수 일자
    private LocalDateTime caseEndDate; // 사건종결일자
    private LocalDateTime occurDate; // 사건발생일자
    private String caseCharge; // 죄명
    private String caseData; // 사건개요
    private String crimeResearchData; // 범죄사실작성
    private int ongoingDate; // 사건 경과 일수 (계산용)
    private CaseStatus caseStatus;  // 사건 상태 (한글 ENUM)
    private String suspect; // 피의자
    private String victim; // 피해자
    private String emName; // 사건담당형사
    private String evidenceFile; // 증거자료 (파일 경로)
    private String closureIdnum; // 종결번호

    public CaseResponseDto (CaseList caseEntity){
        this.id = caseEntity.getId();
        this.caseIdnum = caseEntity.getCaseIdnum();
        this.reportId = caseEntity.getReportId();
        this.reportType = caseEntity.getReportType();
        this.caseReceiveDate = caseEntity.getCaseReceiveDate();
        this.caseEndDate = caseEntity.getCaseEndDate();
        this.occurDate = caseEntity.getOccurDate();
        this.caseCharge = caseEntity.getCaseCharge();
        this.caseData = caseEntity.getCaseData();
        this.crimeResearchData = caseEntity.getCrimeResearchData();
        this.ongoingDate = caseEntity.getOngoingDate();
        this.caseStatus = caseEntity.getCaseStatus();
        this.suspect = caseEntity.getSuspect();
        this.victim = caseEntity.getVictim();
        this.emName = caseEntity.getEvidenceFile();
        this.evidenceFile = caseEntity.getEvidenceFile();
        this.closureIdnum = caseEntity.getClosureIdnum();
    }
}
