package kr.go.knp_system.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.go.knp_system.RequestDTO.LoginRequestDto;
import kr.go.knp_system.RequestDTO.MemberDetails;
import kr.go.knp_system.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그인 컨트롤러
 * 
 */
@Slf4j
@RestController
@RequestMapping("/auth") // 공통 URL prefix
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public LoginController(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto req) {

        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getEmIdNum(), req.getEmPasswd())
        );

        MemberDetails principal = (MemberDetails) auth.getPrincipal();

        String token = jwtUtil.createJwt(
            principal.getUsername(),   // emIdNum을 username으로 쓰는 설정과 맞춰야 함
            "ROLE_USER",
            60 * 60 * 1000L            // 1시간
        );

        return ResponseEntity.ok(Map.of("accessToken", token));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        log.info("로그아웃 요청");
        return ResponseEntity.ok("로그아웃 되었습니다");
    }

}
