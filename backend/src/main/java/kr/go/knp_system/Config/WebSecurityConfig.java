package kr.go.knp_system.Config;

import java.util.List;

import io.swagger.v3.oas.models.OpenAPI;
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

    private final OpenAPI customOpenAPI;

    WebSecurityConfig(OpenAPI customOpenAPI) {
        this.customOpenAPI = customOpenAPI;
    }

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
                "/assets/**",
                "/public",
                "/favicon.ico"
            ).permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/").permitAll()    //react가 렌더링 하는 로그인 페이지
            .loginProcessingUrl("/auth/login")  //react가 보내는 로그인 요청
            // .successHandler(customLcustomOpenAPI)
            .permitAll()
        )
        .logout(logout -> logout
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
        );
        // .logout(logout -> logout.permitAll())

        // return http.build();
        // http
        //         .authorizeHttpRequests(auth -> auth
        //                 .anyRequest().authenticated())
        //         .formLogin(Customizer.withDefaults()) // 🔥 기본 로그인 페이지 사용
        //         .csrf(csrf -> csrf.disable())
        //         .cors(cors -> cors.disable());

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