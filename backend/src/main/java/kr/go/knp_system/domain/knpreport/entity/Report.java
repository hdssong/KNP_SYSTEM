package kr.go.knp_system.domain.knpreport.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import kr.go.knp_system.config.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "report")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "id")
    private long id;

    @Column(name = "report_id", unique = true, length = 100, insertable = false, updatable = false)
    private String reportId; // 접수번호 (yyyy-mm-dd-00001 형식)

    @Column(name = "report_date_time", nullable = false)
    private LocalDate reportDateTime; // 신고일자

    @Column(name = "report_category", length = 20,nullable = false)
    private String reportCategory; // 접수구분 (112, 문자신고 등)

    @Column(name = "report_type", length = 20,nullable = false)
    private String reportType; // 신고 유형 (절도, 폭행 등)(고소,타인신고,피해자신고)

    @Column(name = "location", length = 200,nullable = false)
    private String location; // 신고 발생 장소

    @Column(name = "report_title", length = 100,nullable = false)
    private String reportTitle; // 신고 제목

    @Column(name = "report_contents", length = 512,nullable = false)
    private String reportContents; // 신고 접수 내용

    @Column(name = "report_status", length = 20,nullable = false)
    private String reportStatus; // 신고 상태 (접수, 처리중, 완료, 반려)

    @Column(name = "em_idnum", length = 20,nullable = false)
    private String emIdNum; // 사번

    @Column(name = "org_agency", length = 20,nullable = false)
    private String orgAgency; // 소속

    @PrePersist
    public void prePersist() {
        this.reportDateTime = LocalDate.now(); // yyyy-MM-dd
    }

    @Builder
    public Report(String reportId, LocalDate reportDateTime, String reportCategory, String reportType,
            String location, String reportTitle, String reportContents, String reportStatus, String emIdNum,
            String orgAgency) {
        this.reportId = reportId;
        this.reportDateTime = reportDateTime;
        this.reportCategory = reportCategory;
        this.reportType = reportType;
        this.location = location;
        this.reportTitle = reportTitle;
        this.reportContents = reportContents;
        this.reportStatus = reportStatus;
        this.emIdNum = emIdNum;
        this.orgAgency = orgAgency;
    }

    public void assignReportId(String reportId, LocalDate reportDateTime){
        this.reportId = reportId;
        this.reportDateTime = reportDateTime;
    }

}