package kr.go.knp_system.domain.knpreport.dto;

import java.time.LocalDate;

import kr.go.knp_system.domain.knpreport.entity.Report;
import kr.go.knp_system.domain.member.entity.KnpMember;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportSaveDto {

    private Long id;
    private String reportId;
    private LocalDate reportDateTime;
    private String reportCategory;
    private String reportType;
    private String location;
    private String reportTitle;
    private String reportContents;
    private String reportStatus;
    private String orgAgency;

    @Builder
    public ReportSaveDto(String reportCategory,
            String reportType,
            String location, String reportTitle, String reportContents, String reportStatus) {
        this.reportCategory = reportCategory;
        this.reportType = reportType;
        this.location = location;
        this.reportTitle = reportTitle;
        this.reportContents = reportContents;
        this.reportStatus = reportStatus;
    }

    public Report toEntity(KnpMember knpMember) {
        
        if (knpMember.getHrList() != null && !knpMember.getHrList().isEmpty()) {
            orgAgency = knpMember.getHrList().get(0).getKnpOrg().getOrgFullPathName();
        }
        return Report.builder()
                .reportCategory(reportCategory)
                .reportType(reportType)
                .location(location)
                .reportTitle(reportTitle)
                .reportContents(reportContents)
                .reportStatus(reportStatus)
                .knpMember(knpMember)
                .orgAgency(orgAgency)
                .build();
    }
}
