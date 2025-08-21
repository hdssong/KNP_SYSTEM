package kr.go.knp_system.domain.knpreport.service;

import java.time.LocalDate;

import kr.go.knp_system.domain.knpreport.entity.Report;
import lombok.Getter;

@Getter
public class ReportResponseDto {
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

    public ReportResponseDto (Report entity) {
        this.id = entity.getId();
        this.reportId = entity.getReportId();
        this.reportDateTime = entity.getReportDateTime();
        this.reportCategory = entity.getReportCategory();
        this.reportType = entity.getReportType();
        this.location = entity.getLocation();
        this.reportTitle = entity.getReportTitle();
        this.reportContents = entity.getReportContents();
        this.reportStatus = entity.getReportStatus();
        this.emIdNum = entity.getEmIdNum();
        this.orgAgency = entity.getOrgAgency();
    }
}
