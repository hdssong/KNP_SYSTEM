package kr.go.knp_system.Entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 조직/부서 테이블 계층형 구조
 * 
*/


@Entity
@Table(name = "knp_dept")
@Getter
@NoArgsConstructor
public class KnpDepartment {
    
    @Id
    @Column(name = "org_id")   // 부서코드
    private String org_id;

    private String org_name;   // 부서이름

    private String parent_org_id;  // 상위 부서 ID

    private String org_level;  // 계층 (본청/지방청/경찰서/지구대)

    private String org_full_path;    // 전체 경로 문자열 본청 > 서울청 > 강남서 > 역삼지구대

    private Timestamp create_At;    // 신설할 경우

    private Timestamp update_At;    // 소속이 바뀔경우

    // @OneToMany(mappedBy = "knp_department")
    // private List<KnpMember> members;
    
}
