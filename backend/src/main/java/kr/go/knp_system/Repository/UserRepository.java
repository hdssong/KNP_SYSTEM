package kr.go.knp_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.go.knp_system.Entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, String>{}