package kr.go.knp_system.Controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kr.go.knp_system.Entity.KnpMember;
import kr.go.knp_system.RequestDTO.LoginRequestDto;
import kr.go.knp_system.Service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그인 컨트롤러
 * 
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth") // 공통 URL prefix
public class LoginController {

    private final LoginService loginService;

    @Operation(summary = "로그인 요청", description = "LDAP 인증 후 사용자 정보를 반환합니다.")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @ApiResponse(responseCode = "404", description = "DB에 사용자 정보 없음")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto) {

        log.info("🟢 로그인 시도: {}, {}", requestDto.getEmIdNum(), requestDto.getEmPasswd());


        try {
            // 1. LDAP 인증
            //boolean isAuthenticated = loginService.authenticate(requestDto.getEmIdNum(), requestDto.getEmPasswd());

            // if (!isAuthenticated) {
            //     log.warn("Ldap 인증 실패");

            //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("LDAP 인증 실패");
            // }

            // 2. DB 조회
            Optional<KnpMember> emOpt = loginService.findByEmIdNum(requestDto.getEmIdNum());

            if (emOpt.isEmpty() || !emOpt.get().getEmPasswd().equals(requestDto.getEmPasswd())) {
                log.warn("사용자가 없습니다.",requestDto.getEmIdNum());
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("아이디 또는 비밀번호가 올바르지 않습니다.");
            }

            // 3. 성공
            KnpMember knpUser = emOpt.get();
            System.out.println(knpUser);
            log.info("로그인 성공", knpUser.getEmName());

            return ResponseEntity.ok(knpUser);

        } catch (AuthenticationException e) {
            log.error("❌ 인증 처리 중 예외 발생", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패");
        } catch (Exception e) {
            log.error("❌ 로그인 중 예외 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류");
        }
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃")
    public ResponseEntity<?> logout() {
        log.info("로그아웃 요청");
        return ResponseEntity.ok("로그아웃 되었습니다");
    }

}
