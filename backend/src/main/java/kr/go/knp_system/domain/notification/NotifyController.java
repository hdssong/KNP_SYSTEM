package kr.go.knp_system.domain.notification;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(("/notify"))
// @RequiredArgsConstructor
public class NotifyController {
    
    // private final SseService sseService;

    // @GetMapping
    // public ResponseEntity<SseEmitter> case_in(@Parameter(hidden = true) @AuthenticationPrincipal Principal){
    //     return ResponseEntity.ok(sseService.case_in(Principal))
    // }
    // public SseEmitter case_in(@PathVariable String id){
    //     return sseService.
    // }

}
