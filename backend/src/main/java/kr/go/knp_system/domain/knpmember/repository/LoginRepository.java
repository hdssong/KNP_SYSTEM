package kr.go.knp_system.domain.knpmember.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import kr.go.knp_system.domain.knpmember.entity.KnpMember;

/**
 * 
 * 2. repository 생성
 *  사번으로 찾기
 * 
 * 다음 service
 */
@Repository
public interface LoginRepository extends JpaRepository<KnpMember, String> {
    
    Optional<KnpMember> findByEmIdNum(String emIdNum);

    @Transactional
    public void deleteByEmIdNum(String emIdNum);
}