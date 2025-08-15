package kr.go.knp_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KnpBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnpBackendApplication.class, args);
	}

	//  @Bean
    // CommandLineRunner demo(BCryptPasswordEncoder enc) {
    //     return args -> System.out.println(enc.encode("123"));
    // }
}
