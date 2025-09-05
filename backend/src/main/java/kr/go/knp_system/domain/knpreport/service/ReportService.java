package kr.go.knp_system.domain.knpreport.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.go.knp_system.domain.knpreport.dto.ReportSaveDto;
import kr.go.knp_system.domain.knpreport.repository.ReportRepository;
import kr.go.knp_system.domain.member.entity.KnpMember;
import kr.go.knp_system.domain.member.repository.LoginRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final LoginRepository loginRepository;

    @Transactional(readOnly = true)
    public List<ReportResponseDto> findAll() {
        return reportRepository.findAll().stream()
                .map(report -> new ReportResponseDto(report))

                .collect(Collectors.toList());
    }

    @Transactional
    public Long save(ReportSaveDto reportSaveDto) {

        String emIdNum = SecurityContextHolder.getContext().getAuthentication().getName();
        KnpMember user = loginRepository.findByEmIdNum(emIdNum).orElseThrow(
                () -> new UsernameNotFoundException("작성자 없음 : " + emIdNum));

        return reportRepository.save(reportSaveDto.toEntity(user)).getId();
    }

}
