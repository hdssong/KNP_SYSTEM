package kr.go.knp_system.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;
import kr.go.knp_system.domain.jwt.service.JwtService;
import kr.go.knp_system.filter.JWTFilter;
import kr.go.knp_system.filter.LoginFilter;
import kr.go.knp_system.handler.RefreshTokenLogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // AuthenticationManager가 인자로 받음 AuthenticationConfiguration 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final AuthenticationSuccessHandler loginSuccessHandler;
    private final JwtService jwtService;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration,
            @Qualifier("LoginSuccessHandler") AuthenticationSuccessHandler loginSuccessHandler, JwtService jwtService) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.loginSuccessHandler = loginSuccessHandler;
        this.jwtService = jwtService;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(List.of("Authorization", "Set-Cookie"));
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // 권한 계층
    // @Bean
    // public RoleHierarchy roleHierarchy() {
    // return RoleHierarchyImpl.withRolePrefix("ROLE_")
    // .role(UserRoleType.ADMIN.name()).implies(UserRoleType.USER.name())
    // .build();
    // }
    // AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // securityFilterChain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

                // csrf disable
                .csrf((auth) -> auth.disable())
                // cors 설정
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .formLogin((auth) -> auth.disable())
                .httpBasic((auth) -> auth.disable())
                // 인가 설정
                .authorizeHttpRequests((auth) -> auth
                        // .requestMatchers("/jwt/exchange", "/jwt/refresh").permitAll()
                        // .requestMatchers(HttpMethod.POST, "/user/exist", "/user").permitAll()
                        // .requestMatchers(HttpMethod.GET, "/user").hasRole(UserRoleType.USER.name())
                        // .requestMatchers(HttpMethod.PUT, "/user").hasRole(UserRoleType.USER.name())
                        // .requestMatchers(HttpMethod.DELETE,
                        // "/user").hasRole(UserRoleType.USER.name())
                        .requestMatchers(
                                // "/error",
                                "/auth/login", // 로그인 처리
                                // "/auth/logout", // 로그아웃
                                "/", // React 루트 페이지
                                "/index.html", // vite 정적 자원
                                "/static/**",
                                "/reports",
                                "/reports/**") // vite 정적 자원
                                
                        // "/assets/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(e -> e
                        .authenticationEntryPoint((request, response, authExeption) -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 401응답
                        })
                        .accessDeniedHandler((request, response, authExeption) -> {
                            response.sendError(HttpServletResponse.SC_FORBIDDEN); // 403 응답
                        }))
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션

                // 커스텀 필터 추가
                .addFilterBefore(
                        new LoginFilter(authenticationManager(authenticationConfiguration), loginSuccessHandler),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(
                        new JWTFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.addLogoutHandler(new RefreshTokenLogoutHandler(jwtService)));

        return http.build(); // build type
    }
}