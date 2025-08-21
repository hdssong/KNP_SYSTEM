package kr.go.knp_system.domain.knpcase.dto;

import java.time.LocalDateTime;

import kr.go.knp_system.domain.knpcase.entity.CaseList;
import kr.go.knp_system.domain.knpcase.entity.CaseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CaseSaveDto {

    private long id;
    private String caseIdnum; // 사건번호 (승인 시 생성)
    private String reportId; // 접수 번호 (참조)
    private String reportType; // 접수단서 (고소,타인신고,피해자신고)
    private LocalDateTime caseReceiveDate; // 사건 접수 일자
    private LocalDateTime caseEndDate; // 사건종결일자
    private LocalDateTime occurDate; // 사건발생일자
    private String caseCharge; // 죄명
    private String caseData; // 사건개요
    private int ongoingDate; // 사건 경과 일수 (계산용)
    private CaseStatus caseStatus; // 사건 상태 (한글 ENUM)
    private String suspect; // 피의자
    private String victim; // 피해자
    private String emName; // 사건담당형사
    private String evidenceFile; // 증거자료 (파일 경로)
    private String closureIdnum; // 종결번호

    @Builder
    public CaseSaveDto(String caseIdNum, String reportId, String reportType, String caseCharge, String caseData,
            int ongoingDate, CaseStatus caseStatus, String suspect, String victim, String emName, String closureIdnum) {
        this.caseIdnum = caseIdNum;
        this.reportId = reportId;
        this.reportType = reportType;
        this.caseCharge = caseCharge;
        this.caseData = caseData;
        this.ongoingDate = ongoingDate;
        this.caseStatus = caseStatus;
        this.suspect = suspect;
        this.victim = victim;
        this.emName = emName;
        this.closureIdnum = closureIdnum;
    }

    public CaseList toEntity(){
        return CaseList.builder()
            .caseIdNum(caseIdnum)
            .reportId(reportId)
            .reportType(reportType)
            .caseReceiveDate(caseReceiveDate)
            .caseEndDate(caseEndDate)
            .occurDate(occurDate)
            .caseCharge(caseCharge)
            .caseData(caseData)
            .ongoingDate(ongoingDate)
            .caseStatus(caseStatus)
            .suspect(suspect)
            .victim(victim)
            .emName(emName)
            .closureIdnum(closureIdnum)
            .build();
    }
}
