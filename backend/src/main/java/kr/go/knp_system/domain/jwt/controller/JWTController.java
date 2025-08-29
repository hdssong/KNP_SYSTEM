package kr.go.knp_system.domain.jwt.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.go.knp_system.domain.jwt.dto.JWTResponseDTO;
import kr.go.knp_system.domain.jwt.dto.RefreshRequestDTO;
import kr.go.knp_system.domain.jwt.service.JwtService;

@RestController("/jwt")
public class JWTController {

    private final JwtService jwtService;

    public JWTController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    // Refresh 토큰으로 Access 토큰 재발급 (Rotate 포함)
    @PostMapping(value = "/refresh", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JWTResponseDTO jwtRefreshApi( @Validated @RequestBody RefreshRequestDTO dto) {
        return jwtService.refreshRotate(dto);
    }
}
