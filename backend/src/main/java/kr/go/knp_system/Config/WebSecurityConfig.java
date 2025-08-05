package kr.go.knp_system.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// @EnableWebSecurity
public class WebSecurityConfig {

    // HTTP 보안 설정 정의
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        http
        .csrf(csrf -> csrf.disable())
        .cors(cors -> cors.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/auth/login",         // 로그인 처리
                "/auth/logout",        // 로그아웃
                "/",                   // React 루트 페이지
                "/index.html",         // vite 정적 자원
                "/static/**",          // vite 정적 자원
                "/assets/**"
                // "/public",
                // "/favicon.ico"
            ).permitAll()
            .anyRequest().authenticated()
        );
        // .formLogin(form -> form
        //     .loginPage("/")    //react가 렌더링 하는 로그인 페이지
        //     .loginProcessingUrl("/auth/login")
        //     .permitAll()  //react가 보내는 로그인 요청
        // )
        // .logout(logout -> logout
        //         .logoutUrl("/auth/logout")
        //         .logoutSuccessUrl("/")
        //         .invalidateHttpSession(true)
        // );
        return http.build();

    }
}