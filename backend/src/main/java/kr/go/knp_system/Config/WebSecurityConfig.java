package kr.go.knp_system.Config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
// @EnableWebSecurity
public class WebSecurityConfig {

    // HTTP 보안 설정 정의
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http
        // .authorizeHttpRequests(auth -> auth
        // .requestMatchers("/", "/index.html", "/assets/**", "/static/**",
        // "/login").permitAll()
        // .anyRequest().authenticated()
        // )
        // .formLogin(form -> form
        // .loginPage("/login").permitAll()
        // )
        // .logout(logout -> logout.permitAll())
        // .csrf(csrf -> csrf.disable())
        // .cors(cors -> cors.disable()); // 에러 나는 부분 제거

        // return http.build();
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults()) // 🔥 기본 로그인 페이지 사용
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());

        return http.build();

    }

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource source = new LdapContextSource();
        source.setUrl("ldap://localhost:8390");
        source.setBase("dc=springframework,dc=org");

        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(BaseLdapPathContextSource contextSource) {
        BindAuthenticator authenticator = new BindAuthenticator(contextSource);
        authenticator.setUserDnPatterns(new String[] { "uid={0},ou=people" });
        return new ProviderManager(new LdapAuthenticationProvider(authenticator));
    }

}