package kr.go.knp_system.domain.notification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotifyRepository extends JpaRepository<Notification,Long>{
   
}