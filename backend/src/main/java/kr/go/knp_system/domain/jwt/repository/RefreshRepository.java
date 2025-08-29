package kr.go.knp_system.domain.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import kr.go.knp_system.domain.jwt.entity.RefreshEntity;

public interface RefreshRepository extends JpaRepository<RefreshEntity,Long> {

    //refresh 토큰 존재 유무
    @Transactional(readOnly = true)
    Boolean existsByRefresh(String refreshToken);

    @Transactional
    void deleteByRefresh(String refresh);

    @Transactional
    void deleteByEmIdNum(String emIdNum);

    @Transactional
    Boolean existsByEmIdNum(String emIdNum);

    @Transactional
    Optional<RefreshEntity> findByRefresh(String refresh);

}
