package kr.go.knp_system.domain.knpreport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.go.knp_system.domain.knpreport.entity.Report;

public interface ReportRepository extends JpaRepository<Report,Long>{

    // @Query("SELECT p FROM report ORDER BY p.id DESC")    
    List<Report> findAll();
} 
