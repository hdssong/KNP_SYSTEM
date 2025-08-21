package kr.go.knp_system.domain.knpcase.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CaseStatus {

    RECEIVED("접수"),
    ASSIGNED("승인"),
    IN_PROGRESS("진행 중"),
    COMPLETED("종결"),
    REJECTED("기각");

    private final String label; // 한글 표시/DB 값

    CaseStatus(String label) {
        this.label = label;
    }

    /** 응답(JSON)에는 한글로 나가게 */
    @JsonValue
    public String getLabel() {
        return label;
    }

    /** 요청(JSON)에서 한글로 들어와도 매핑되게 */
    @JsonCreator
    public static CaseStatus from(String value) {
        if (value == null)
            return null;
        // 한글로 들어오면 label 매칭, 영어 코드가 들어와도 허용
        for (CaseStatus s : values()) {
            if (s.label.equals(value) || s.name().equalsIgnoreCase(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Unknown CaseStatus: " + value);
    }
}
