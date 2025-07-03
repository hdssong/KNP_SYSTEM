package kr.go.knp_system.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
// @RequiredArgsConstructor
// @RequestMapping("/api")
public class LoginController {

    @GetMapping("/api/test")
    public String hello() {
        return "test";
    }
    
    
}
