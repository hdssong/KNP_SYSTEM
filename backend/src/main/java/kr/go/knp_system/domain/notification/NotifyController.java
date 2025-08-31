package kr.go.knp_system.domain.notification;

import java.security.Principal;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(("/notify"))
public class NotifyController {
    
    //private final NotifyService notifyService;

    // public NotifyController(NotifyService notifyService){
    //     this.notifyService = notifyService;
    // }

    // @GetMapping(value = "/report_notify",produces = "text/event-stream")
    // public Notification report_notify(@AuthenticationPrincipal User principal, @RequestHeader(value = "Last-Event-ID", required = false,defaultValue = "")String laseEvnetId){
    //     return notifyService.report_notify(principal.getName(),laseEvnetId);
    // }

}
