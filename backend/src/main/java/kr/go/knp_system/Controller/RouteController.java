package kr.go.knp_system.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RouteController {

    // @RequestMapping(value = {
    // "/",
    // "/login",
    // "/dashboard",
    // "/{path:^(?!api|auth|static|index\\.html|favicon\\.ico|error|assets).*}/**"
    // })
    // public String forwardToIndex() {
    // return "forward:/index.html";
    // }

    // 로그인 경로는 index.html 반환하지 않도록 제외
    // @RequestMapping({
    //         "/", "/dashboard", "/{path:^(?!api|auth|static|login).*}/**"
    // })
    // public String forwardToIndex() {
    //     return "forward:/index.html";
    // }
}