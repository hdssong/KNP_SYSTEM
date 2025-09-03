package kr.go.knp_system.domain.knpreport.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.go.knp_system.domain.knpreport.dto.ReportSaveDto;
import kr.go.knp_system.domain.knpreport.service.ReportResponseDto;
import kr.go.knp_system.domain.knpreport.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportApiController {

    private final ReportService reportService;

    public ReportApiController (ReportService reportService){
        this.reportService = reportService;
    }

    // 신고 접수 내역
    @GetMapping("/report_list")
    public List<ReportResponseDto> getAllReports(){
        return reportService.findAll();
    }

    // 신고 접수 등록
    @PostMapping("/regist_save")
    public Long save(@RequestBody ReportSaveDto requestDto,Authentication authentication){
        return reportService.save(requestDto);
    }
}
