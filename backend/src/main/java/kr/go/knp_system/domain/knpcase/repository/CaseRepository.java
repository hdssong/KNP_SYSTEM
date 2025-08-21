package kr.go.knp_system.domain.knpcase.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.go.knp_system.domain.knpcase.entity.CaseList;

public interface CaseRepository extends JpaRepository <CaseList,Long>{
    
    // 사건 전체 조회
    List<CaseList> findAll();
    Optional<CaseList> findById(Long id);
    Optional<CaseList> findByCaseIdnum(String caseIdnum); // 사건번호로 조회
}
