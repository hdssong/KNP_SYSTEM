package kr.go.knp_system.domain.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.go.knp_system.domain.member.dto.LoginResponseDto;
import kr.go.knp_system.domain.member.service.KnpMemberService;

@RestController
@RequestMapping("/home")
public class LoginController {
    
    private final KnpMemberService knpMemberService;

    public LoginController(KnpMemberService knpMemberService){
        this.knpMemberService = knpMemberService;
    }

    // 로그인 하면 나오는 정보
    @GetMapping("/member_info")
    public ResponseEntity<LoginResponseDto> me(){
        return ResponseEntity.ok(knpMemberService.getInfo());
    } 
    
}
