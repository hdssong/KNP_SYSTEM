package kr.go.knp_system.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 조직/부서 테이블 계층형 구조
 * @Param npaCode           // 경찰청 코드
 * @Param npaName           // 경찰청 이름
 * @Param psOrgCode         // 경찰서 코드
 * @Param psOrgName         // 경찰서 이름
 * @Param deptCode          // 부서/지구대 코드
 * @Param divName           // 부서/지구대 이름
 * @Param teamUnitCode      // 팀/파출소 코드
 * @Param teamUnitName      // 팀/파출소 이름
 * @Param depth             // 계층 레벨 (1=청, 2=서, 3=부서/지구대, 4=팀/파출소)
 * @Param orgCode           // 조직 전체 코드(업무키, UNIQUE)
 * @Param orgFullPath       // 코드/한글 중 선택 설계
 * @Param orgFullPathName   // 한글 전체 경로(있으면 좋음)
*/

@Entity
@Getter
@Table(name = "knp_org")
@NoArgsConstructor
public class KnpOrg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id")
    private Long rowId;

    @Column(name = "org_code", length = 30, unique = true, nullable = false)
    private String orgCode;

    @Column(name = "org_full_path_name", length = 100, nullable = false)
    private String orgFullPathName;




    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "row_id", nullable = false)
    // private Long rowId;

    // @Column(name = "npa_code", length = 30)          
    // private String npaCode;

    // @Column(name = "npa_name", length = 100)          
    // private String npaName;

    // @Column(name = "ps_org_code", length = 30)     
    // private String psOrgCode;

    // @Column(name = "ps_org_name", length = 100)       
    // private String psOrgName;

    // @Column(name = "dept_code", length = 30)         
    // private String deptCode;

    // @Column(name = "div_name", length = 100)        
    // private String divName;

    // @Column(name = "team_unit_code", length = 30)    
    // private String teamUnitCode;

    // @Column(name = "team_unit_name", length = 100)    
    // private String teamUnitName;

    // @Column(name = "depth", nullable = false)        
    // private Byte depth;

    // @Column(name = "org_code", length = 30, nullable = false) 
    // private String orgCode;

    // @Column(name = "org_full_path", length = 255, nullable = false) 
    // private String orgFullPath;

    // @Column(name = "org_full_path_name", length = 255)
    // private String orgFullPathName;
}
