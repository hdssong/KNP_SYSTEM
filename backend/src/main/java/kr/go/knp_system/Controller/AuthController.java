package kr.go.knp_system.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    // @Autowired
    // private LdapAuthService ldapAuthService;

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    //     boolean isValid = ldapAuthService.authenticate(request.getUsername(), request.getPassword());

    //     if (isValid) {
    //         // 로그인 성공: JWT 발급 또는 세션 생성 가능
    //         return ResponseEntity.ok().body("Login OK");
    //     } else {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    //     }
    // }
}
