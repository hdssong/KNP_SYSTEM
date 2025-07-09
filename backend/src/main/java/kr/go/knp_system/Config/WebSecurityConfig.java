package kr.go.knp_system.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.web.SecurityFilterChain;

@Configuration
// @EnableWebSecurity
public class WebSecurityConfig {

    // HTTP 보안 설정 정의
    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http
    //         .cors(Customizer.withDefaults())    //Cors 기본 허용 
    //         .authorizeHttpRequests(auth -> auth
    //             .requestMatchers("/api/auth/**").permitAll()    // 인증 없이 허용
    //             .anyRequest().authenticated()   // 나머지는 인증 필요
    //         )
    //         .formLogin(Customizer.withDefaults())
    //         // .formLogin(AbstractHttpConfigurer::disable) 
    //         .httpBasic(AbstractHttpConfigurer::disable);

    //     return http.build();
    // }

}