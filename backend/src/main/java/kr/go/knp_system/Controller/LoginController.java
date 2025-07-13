package kr.go.knp_system.Controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.go.knp_system.DTO.LoginRequestDto;
import kr.go.knp_system.Entity.KnpMember;
import kr.go.knp_system.Service.LoginService;
import lombok.RequiredArgsConstructor;

/**
 * 로그인
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto) {
        try {
            // 1. LDAP 인증 시도
            Authentication authentication = authenticationManager.authenticate(
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
