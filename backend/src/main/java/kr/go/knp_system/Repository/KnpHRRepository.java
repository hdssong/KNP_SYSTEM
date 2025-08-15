package kr.go.knp_system.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.go.knp_system.Entity.KnpHR;

public interface KnpHRRepository extends JpaRepository<KnpHR,String>{

    Optional<KnpHR> findByEmIdNum(String emIdNum);

}