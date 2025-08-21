package kr.go.knp_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.go.knp_system.domain.knpmember.service.KnpMemberService;
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

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        log.info("로그아웃 요청");
        return ResponseEntity.ok("로그아웃 되었습니다");
    }

}
