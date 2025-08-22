package kr.go.knp_system.domain.knpcase.dto;

import kr.go.knp_system.domain.knpcase.entity.CaseList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CaseUpdateDto {

    private String caseData; // 사건개요
    private String crimeResearchData; // 범죄사실작성
    private String evidenceFile; // 증거자료

    @Builder
    public CaseUpdateDto(String caseData, String crimeResearchData, String evidenceFile) {
        this.caseData = caseData;
        this.crimeResearchData = crimeResearchData;
        this.evidenceFile = evidenceFile;
    }

    public CaseList toEntity() {
        return CaseList.builder()
                .caseData(caseData)
                .crimeResearchData(crimeResearchData)
                .evidenceFile(evidenceFile)
                .build();
    }

}
