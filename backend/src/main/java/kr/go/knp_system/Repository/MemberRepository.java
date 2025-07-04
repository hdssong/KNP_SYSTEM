package kr.go.knp_system.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.go.knp_system.Entity.KnpMember;


public interface MemberRepository extends JpaRepository<KnpMember, String> {

    Optional<KnpMember> findByName(String name);
    boolean existsByName(String name);
    
}