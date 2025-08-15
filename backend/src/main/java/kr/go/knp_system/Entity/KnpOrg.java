package kr.go.knp_system.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 조직/부서 테이블 계층형 구조
 * 
*/

@Entity
// @Table(
//     name = "knp_org",
//     indexes = {
//         @Index(name = "idx_org_full_path", columnList = "org_full_path")
//     },
//     uniqueConstraints = {
//         @UniqueConstraint(name = "knp_org_unique", columnNames = "org_code")
//     }
// )
@Getter
@NoArgsConstructor
public class KnpOrg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id", nullable = false)
    private Long rowId; // 내부 PK (AUTO_INCREMENT)

    // 레벨별 컬럼: 계층 특성상 nullable=true 로 둔다
    @Column(name = "npa_code", length = 30)           // 경찰청 코드
    private String npaCode;

    @Column(name = "npa_name", length = 100)          // 경찰청 이름
    private String npaName;

    @Column(name = "ps_org_code", length = 30)        // 경찰서 코드
    private String psOrgCode;

    @Column(name = "ps_org_name", length = 100)       // 경찰서 이름
    private String psOrgName;

    @Column(name = "dept_code", length = 30)          // 부서/지구대 코드
    private String deptCode;

    @Column(name = "div_name", length = 100)          // 부서/지구대 이름
    private String divName;

    @Column(name = "team_unit_code", length = 30)     // 팀/파출소 코드
    private String teamUnitCode;

    @Column(name = "team_unit_name", length = 100)    // 팀/파출소 이름
    private String teamUnitName;

    @Column(name = "depth", nullable = false)         // 계층 레벨 (1=청, 2=서, 3=부서/지구대, 4=팀/파출소)
    private Byte depth;

    @Column(name = "org_code", length = 30, nullable = false) // 조직 전체 코드(업무키, UNIQUE)
    private String orgCode;

    @Column(name = "org_full_path", length = 255, nullable = false) // 코드/한글 중 선택 설계
    private String orgFullPath;

    @Column(name = "org_full_path_name", length = 255) // 한글 전체 경로(있으면 좋음)
    private String orgFullPathName;

    // @OneToMany(mappedBy = "knpOrg", fetch = FetchType.LAZY) //HR 엔터티 매핑(1:N)
    // private List<KnpHR> hrList;

    // @OneToMany(mappedBy = "knpOrg",fetch = FetchType.LAZY)  //knpMember 엔터티 매핑 (1:N)
    // private List<KnpMember> knpMembers;
    

}
