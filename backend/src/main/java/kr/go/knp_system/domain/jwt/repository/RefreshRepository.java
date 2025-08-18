package kr.go.knp_system.domain.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import kr.go.knp_system.domain.jwt.entity.RefreshEntity;

public interface RefreshRepository extends JpaRepository<RefreshEntity,String> {

    @Transactional(readOnly = true)
    //refresh 토큰 존재 유무
    Boolean existsByRefresh(String refreshToken);

    @Transactional
    void deleteByRefresh(String refresh);

    @Transactional
    void deleteByEmIdNum(String emIdNum);

}
