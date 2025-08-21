package kr.go.knp_system.domain.knpcase.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.go.knp_system.domain.knpcase.dto.CaseResponseDto;
import kr.go.knp_system.domain.knpcase.dto.CaseSaveDto;
import kr.go.knp_system.domain.knpcase.entity.CaseList;
import kr.go.knp_system.domain.knpcase.repository.CaseRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CaseService {

    private final CaseRepository caseRepository;

    // 사건 조회 (전체 리스트)
    @Transactional(readOnly = true)
    public List<CaseResponseDto> findAll() {
        return caseRepository.findAll().stream()
                .map(caseList -> new CaseResponseDto(caseList))
                .collect(Collectors.toList());
    }

    @Transactional
    public CaseResponseDto findCaseById(Long id) {

        CaseList caseOne = caseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사건을 찾을 수 없습니다."));

        return new CaseResponseDto(caseOne);
    }

    @Transactional
    public CaseResponseDto getCaseByCaseIdnum(String caseIdnum) {
        CaseList caseEntity = caseRepository.findByCaseIdnum(caseIdnum)
                .orElseThrow(() -> new IllegalArgumentException("해당 사건이 존재하지 않습니다. 사건번호=" + caseIdnum));
        return new CaseResponseDto(caseEntity);
    }

    // 사건 등록
    @Transactional
    public Long save(CaseSaveDto caseSaveDto) {
        return caseRepository.save(caseSaveDto.toEntity()).getId();
    }
    // 사건 수정

}
