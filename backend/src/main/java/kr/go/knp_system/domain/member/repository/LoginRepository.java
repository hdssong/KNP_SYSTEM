package kr.go.knp_system.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import kr.go.knp_system.domain.member.dto.LoginResponseDto;
import kr.go.knp_system.domain.member.entity.KnpMember;

@Repository
public interface LoginRepository extends JpaRepository<KnpMember, String> {

    Optional<KnpMember> findByEmIdNum(String emIdNum);

    @Transactional
    public void deleteByEmIdNum(String emIdNum);

    // 사원 조회 해오는 쿼리
    @Query("""
        select new kr.go.knp_system.domain.member.dto.LoginResponseDto(
            m.emIdNum,
            h.emName,
            o.orgFullPathName,
            r.rankName
        )
        from KnpHR h
        join h.knpMember m
        join h.knpOrg o
        join h.knpRank r
        where m.emIdNum = :emIdNum
        order by h.hrId desc
    """)
    Optional<LoginResponseDto> findHomeUserInfo(@Param("emIdNum") String emIdNum);

}