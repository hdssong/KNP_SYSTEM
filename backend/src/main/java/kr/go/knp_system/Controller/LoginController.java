package kr.go.knp_system.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.go.knp_system.domain.knpmember.dto.LoginRequestDto;
import kr.go.knp_system.domain.knpmember.dto.MemberDetails;
import kr.go.knp_system.domain.knpmember.service.KnpMemberService;
import kr.go.knp_system.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그인 컨트롤러
 * 
 */
@Slf4j
@RestController
@RequestMapping("/auth") // 공통 URL prefix
public class LoginController {

    private final KnpMemberService knpMemberService;

    public LoginController(KnpMemberService knpMemberService) {
        this.knpMemberService = knpMemberService;
    }

    // 자체 로그인 유저 존재 확인
    // @PostMapping(value = "/user/exist", consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<Boolean> existUserApi(
    //         @Validated(LoginRequestDto.class) @RequestBody LoginRequestDto dto) {
    //     return ResponseEntity.ok(knpMemberService.exist(dto));
    // }

    // @PostMapping(value = "/login")
    // public ResponseEntity<?> login(@RequestBody LoginRequestDto req) {

    // }

    // private final AuthenticationManager authenticationManager;
    // private final JWTUtil jwtUtil;

    // public LoginController(AuthenticationManager authenticationManager, JWTUtil
    // jwtUtil) {
    // this.authenticationManager = authenticationManager;
    // this.jwtUtil = jwtUtil;
    // }

    // @PostMapping(value = "/login")
    // public ResponseEntity<?> login(@RequestBody LoginRequestDto req) {

    // Authentication auth = authenticationManager.authenticate(
    // new UsernamePasswordAuthenticationToken(req.getEmIdNum(), req.getEmPasswd())
    // );

    // MemberDetails principal = (MemberDetails) auth.getPrincipal();

    // String token = jwtUtil.createJWT(
    // principal.getUsername(), // emIdNum을 username으로 쓰는 설정과 맞춰야 함
    // "ROLE_USER",
    // 60 * 60 * 1000L // 1시간
    // );

    // return ResponseEntity.ok(Map.of("accessToken", token));
    // }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        log.info("로그아웃 요청");
        return ResponseEntity.ok("로그아웃 되었습니다");
    }

}
