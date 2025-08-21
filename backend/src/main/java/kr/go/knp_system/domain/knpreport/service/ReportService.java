package kr.go.knp_system.domain.knpreport.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.go.knp_system.domain.knpreport.dto.ReportSaveDto;
import kr.go.knp_system.domain.knpreport.repository.ReportRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Transactional(readOnly = true)
    public List<ReportResponseDto> findAll() {
        return reportRepository.findAll().stream()
                .map(report -> new ReportResponseDto(report))

                .collect(Collectors.toList());
    }

    @Transactional
    public Long save(ReportSaveDto reportSaveDto) {
        return reportRepository.save(reportSaveDto.toEntity()).getId();
    }

    
}
