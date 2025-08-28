package kr.go.knp_system.domain.jwt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.go.knp_system.domain.jwt.dto.JWTResponseDTO;
import kr.go.knp_system.domain.jwt.dto.RefreshRequestDTO;
import kr.go.knp_system.domain.jwt.entity.RefreshEntity;
import kr.go.knp_system.domain.jwt.repository.RefreshRepository;
import kr.go.knp_system.util.JWTUtil;

@Service
public class JwtService {

    private final RefreshRepository refreshRepository;

    public JwtService(RefreshRepository refreshRepository) {
        this.refreshRepository = refreshRepository;
    }

    // Refresh 토큰으로 Access 토큰 재발급 로직 (Rotate 포함) <-- 이건 추후에 작성 (만료될 경우)

    // 1. JWT Refresh 토큰 발급 후 저장 메소드
    @Transactional
    public void addRefresh(String emIdNum,String refreshToken) {

        // 같은 사용자로 기존 토큰 있으면 지우로 새로 저장
        if (refreshRepository.existsByEmIdNum(emIdNum)) {
            refreshRepository.deleteByEmIdNum(emIdNum);            
        }

        RefreshEntity entity = RefreshEntity.builder()
                .emIdNum(emIdNum)
                .refresh(refreshToken)
                .build();

        refreshRepository.save(entity);
    }

    // JWT Refresh 존재 확인 메소드
    @Transactional(readOnly = true)
    public boolean existsRefresh(String refreshToken) {
        return refreshRepository.existsByRefresh(refreshToken);
    }

    // JWT Refresh 토큰 삭제 메소드
    @Transactional
    public void removeRefresh(String refreshToken) {
        refreshRepository.deleteByRefresh(refreshToken);
    }

    // 특정 유저 Refresh 토큰 모두 삭제 (탈퇴)
    @Transactional
    public void removeRefreshUser(String emIdNum) {
        refreshRepository.deleteByEmIdNum(emIdNum);
    }

    // Refresh 토큰으로 Access 토큰 재발급 로직 (Rotate 포함)
    @Transactional
    public JWTResponseDTO refreshRotate(RefreshRequestDTO dto) {
        
        // 검증
        String refreshToken = dto.getRefreshToken();

        // Refresh 토큰 검증
        Boolean isValid = JWTUtil.isValid(refreshToken, false);
        
        if (!isValid) {
            throw new RuntimeException("유효하지 않은 refreshToken입니다.");
        }

        if (!existsRefresh(refreshToken)) {
            throw new RuntimeException("등록되지 않은 토큰 입니다.");
        }

        // 정보 추출
        String emIdNum = JWTUtil.getUsername(refreshToken);
        String role = JWTUtil.getRole(refreshToken);

        // 토큰 생성
        String newAccessToken = JWTUtil.createJWT(emIdNum, role, true);
        String newRefreshToken = JWTUtil.createJWT(emIdNum, role, false);

        // 기존 Refresh 토큰 DB 삭제 후 신규 추가
        // RefreshEntity newRefreshEntity = RefreshEntity.builder()
        //         .emIdNum(emIdNum)
        //         .refresh(newRefreshToken)
        //         .build();

        removeRefresh(refreshToken);
        addRefresh(emIdNum, newRefreshToken);
        
        // refreshRepository.save(newRefreshEntity);

        return new JWTResponseDTO(newAccessToken, newRefreshToken);
    }

}
