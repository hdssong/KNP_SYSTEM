package kr.go.knp_system.Controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.go.knp_system.DTO.LoginRequestDto;
import kr.go.knp_system.Entity.KnpMember;
import kr.go.knp_system.Service.LoginService;

/**
 * 로그인
 */

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager = null;
    private final LoginService loginService = null;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto) {
        try {
            // 1. LDAP 인증 시도
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmIdNum(), // UID 기준 인증
                            requestDto.getPassword()));

            // 2. DB 조회
            Optional<KnpMember> userOpt = loginService.findByEmIdNum(requestDto.getEmIdNum());
            if (userOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DB에 사용자 정보가 없습니다.");
            }

            // 3. 인증 성공 + DB 조회 성공
            return ResponseEntity.ok(userOpt.get());

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("LDAP 인증 실패");
        }
    }
}
