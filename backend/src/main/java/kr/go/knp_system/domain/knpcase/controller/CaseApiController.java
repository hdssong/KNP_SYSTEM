package kr.go.knp_system.domain.knpcase.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.go.knp_system.domain.knpcase.dto.CaseResponseDto;
import kr.go.knp_system.domain.knpcase.dto.CaseSaveDto;
import kr.go.knp_system.domain.knpcase.service.CaseService;

@RestController
@RequestMapping("/cases")
public class CaseApiController {

    private final CaseService caseService;

    public CaseApiController(CaseService caseService) {
        this.caseService = caseService;
    }

    // 전체 조회
    @GetMapping
    public List<CaseResponseDto> getAllCases() {
        return caseService.findAll();
    }

    // 1. PK(ID)로 조회
    @GetMapping("/{id}")
    public ResponseEntity<CaseResponseDto> getCase(@PathVariable("id") Long id) {
        return ResponseEntity.ok(caseService.findCaseById(id));
    }

    // 2. 사건번호(caseIdnum)로 조회
    @GetMapping("/by-caseId/{caseIdnum}")
    public ResponseEntity<CaseResponseDto> getCaseByCaseIdnum(@PathVariable("caseIdnum") String caseIdnum) {
        return ResponseEntity.ok(caseService.getCaseByCaseIdnum(caseIdnum));
    }

    // 사건 등록
    @PostMapping("/cases_save")
    public Long save(@RequestBody CaseSaveDto caseSaveDto) {
        return caseService.save(caseSaveDto);
    }

}
