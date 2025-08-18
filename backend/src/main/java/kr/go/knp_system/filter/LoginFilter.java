package kr.go.knp_system.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFilter extends AbstractAuthenticationProcessingFilter{
    
    public static final String SPRING_SECURITY_FORM_EMIDNUM_KEY = "emIdNum";

    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "emPasswd";

    private static final RequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST, "/auth/login");

    private String emIDNumParam = SPRING_SECURITY_FORM_EMIDNUM_KEY;

    private String emPasswdParam = SPRING_SECURITY_FORM_PASSWORD_KEY;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public LoginFilter(AuthenticationManager authenticationManager,AuthenticationSuccessHandler authenticationSuccessHandler){
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER,authenticationManager);
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    public Authentication attemptAuthentication (HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supproted : " + request.getMethod());
        }
        
        Map<String,String> loginMap;

        try {
            ObjectMapper objMap = new ObjectMapper();
            ServletInputStream inputStream = request.getInputStream();

            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            loginMap = objMap.readValue(messageBody, new TypeReference<>() {  
            });

        } catch (Exception e) {
            throw new  RuntimeException();
        }

        String emIdNum = loginMap.get(emIDNumParam);
        emIdNum = (emIdNum != null) ? emIdNum.trim() : "";

        String emPasswd = loginMap.get(emPasswdParam);
        emPasswd = (emPasswd != null) ? emPasswd : "";

        System.out.println(emIdNum);
        System.out.println(emPasswd);
        
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(emIdNum,emPasswd);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,FilterChain chain,Authentication authentication) throws IOException, ServletException{
        
        authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
    }









    //주입
    // private final AuthenticationManager authenticationManager;
    // private final JWTUtil jwtUtil;

    // // public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil){
    // //     this.authenticationManager = authenticationManager;
    // //     this.jwtUtil = jwtUtil;
    // // }

    // @Override
    // public Authentication attemptAuthentication (HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{

    //     // 클라이언트 요쳥에서 username, password 공유
    //     String emIdNum = obtainUsername(request);
    //     String emPasswd = obtainPassword(request);

    //     System.out.println(emIdNum);

    //     //스프링 시큐리티에서 Username과 password를 검증하기 위해서는 token에 담아야 함
    //     UsernamePasswordAuthenticationToken authTocken = new UsernamePasswordAuthenticationToken(emIdNum,emPasswd,null);

    //     // token에 담은 검증을 위한 authenticationManager로 전달
    //     return authenticationManager.authenticate(authTocken);
    // }

    // // 로그인 성공시 실행하는 메소드(여기서 JWT 발급)

    // protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,FilterChain chain,Authentication authentication){
        
    //     //UserDetails
    //     MemberDetails memberDetails = (MemberDetails)authentication.getPrincipal();

    //     String emName = memberDetails.getUsername();

    //     Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    //     Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
    //     GrantedAuthority auth = iterator.next();

    //     String role = auth.getAuthority();

    //     String token = jwtUtil.createJwt(emName, role, 60*60*10L);

    //     response.addHeader("Authorization", "Bearer " + token);

    // }

    // protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,FilterChain chain){
    //     //로그인 실패시 401 응답 코드 반환
    //     response.setStatus(401);
    // }

}
