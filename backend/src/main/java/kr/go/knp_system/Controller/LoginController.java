package kr.go.knp_system.Controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
// import kr.go.knp_system.DTO.LoginDto;
import kr.go.knp_system.Entity.KnpMember;
import kr.go.knp_system.Service.LoginService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 로그인
 * 
 * @param loginId - 로그인 ID (사번)
 * @param password -  비밀번호
 * 
 * ** */


@RestController
//@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

   // private final LoginService loginService;


    // @PostMapping("/login")
    // public ResponseEntity<?> login (@RequestBody LoginDto loginDto) {
        
    // }
    
    //로그인 첫 화면
    @GetMapping("/api/login")
    public String login() {
        return "login page";
    }

    
    // @GetMapping("/{name}")
    // public ResponseEntity<KnpMember> getUser(@PathVariable String name) {
    //     return ResponseEntity.ok(loginService.findByName(name));
    // }

    // @PostMapping
    // public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    //     KnpMember user = loginService.findByName(loginRequest.getName());

    //     // if (!user.getPassword().equals(loginRequest.getPassword())) {
    //     //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 틀렸습니다.");
    //     // }

    //     return ResponseEntity.ok("로그인 성공");
    // }
}
