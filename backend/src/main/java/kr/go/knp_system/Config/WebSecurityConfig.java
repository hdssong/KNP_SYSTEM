package kr.go.knp_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import kr.go.knp_system.jwt.JWTUtil;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // AuthenticationManager가 인자로 받음 AuthenticationConfiguration 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    public WebSecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    // AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // HTTP 보안 설정 정의
    // http 인자
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

                // csrf disable
                .csrf((auth) -> auth.disable())
                // form로그인 방식 disable
                .formLogin((auth) -> auth.disable())
                // http basic 인증 방식 disable
                .httpBasic((auth) -> auth.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(
                                // "/error",
                                "/auth/login", // 로그인 처리
                                // "/auth/logout", // 로그아웃
                                "/", // React 루트 페이지
                                "/index.html", // vite 정적 자원
                                "/static/**") // vite 정적 자원
                                // "/assets/**")
                        .permitAll()
                        .anyRequest().authenticated())
                        //필터 추가
                // .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
                //         UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션
                                                                                                                 // 설정

        return http.build(); // build type
    }
}