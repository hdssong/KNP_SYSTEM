package kr.go.knp_system.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig{

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  //모든경로
                .allowedOrigins("http://localhost:3000")    // 허용할 origin React dev 서버
                .allowedMethods("*")   // GET, POST 등
                .allowedHeaders("*")
                .allowCredentials(true);
            }
        };
}
}

