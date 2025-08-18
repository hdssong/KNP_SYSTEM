package kr.go.knp_system.domain.knpmember.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.go.knp_system.domain.knpmember.entity.KnpHR;

@Repository
public interface KnpHRRepository extends JpaRepository<KnpHR, String> {

    // @Query("""
    //         select hr from knpHR hr join fetch hr.knpOrg o
    //                                 join fetch hr.knpRank r
    //         where hr.emIdNum = :emIdNum
    //         order by hr.hrId desc
    //         """)
    //List<KnpHR> findAllByEmIdNumWithJoins(String emIdNum);

    // default Optional<KnpHR> findLatestByEmIdNumWithJoins(String emIdNum) {
    //     List<KnpHR> list = findAllByEmIdNumWithJoins(emIdNum);
    //     return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    // }
}