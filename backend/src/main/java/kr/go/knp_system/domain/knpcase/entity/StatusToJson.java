package kr.go.knp_system.domain.knpcase.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusToJson implements AttributeConverter<CaseStatus,String> {

    @Override
    public String convertToDatabaseColumn(CaseStatus attribute) {
        return attribute == null ? null : attribute.getLabel(); // 한글 저장
    }

    @Override
    public CaseStatus convertToEntityAttribute(String dbData) {
        throw new UnsupportedOperationException("Unimplemented method 'convertToEntityAttribute'");

    }
    
}
