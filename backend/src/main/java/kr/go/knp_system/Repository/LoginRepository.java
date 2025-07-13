package kr.go.knp_system.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.go.knp_system.Entity.KnpMember;

/**
 * 
 * 2. repository 생성
 *  사번으로 찾기
 * 
 * 다음 service
 */
public interface LoginRepository extends JpaRepository<KnpMember, String> {

    Optional<KnpMember> findByEmIdNum(String emIdNum);  // 사번 찾아라
    //boolean existsByName(String name);
    
}