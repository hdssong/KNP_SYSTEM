package kr.go.knp_system.domain.knpreport.dto;

import java.time.LocalDate;

import kr.go.knp_system.domain.knpreport.entity.Report;
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

    private String emIdNum;

    private String orgAgency;

    @Builder
    public ReportSaveDto(String reportCategory,
            String reportType,
            String location, String reportTitle, String reportContents, String reportStatus, String emIdNum,
            String orgAgency) {
        this.reportCategory = reportCategory;
        this.reportType = reportType;
        this.location = location;
        this.reportTitle = reportTitle;
        this.reportContents = reportContents;
        this.reportStatus = reportStatus;
        this.emIdNum = emIdNum;
        this.orgAgency = orgAgency;
    }

    public Report toEntity() {
        return Report.builder()
                .reportCategory(reportCategory)
                .reportType(reportType)
                .location(location)
                .reportTitle(reportTitle)
                .reportContents(reportContents)
                .reportStatus(reportStatus)
                .emIdNum(emIdNum)
                .orgAgency(orgAgency)
                .build();
    }
}
