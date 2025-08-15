package kr.go.knp_system.jwt;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.go.knp_system.RequestDTO.MemberDetails;

public class LoginFilter extends UsernamePasswordAuthenticationFilter{
    
    //주입
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;


    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication (HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{

        // 클라이언트 요쳥에서 username, password 공유
        String emIdNum = obtainUsername(request);
        String emPasswd = obtainPassword(request);

        System.out.println(emIdNum);

        //스프링 시큐리티에서 Username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authTocken = new UsernamePasswordAuthenticationToken(emIdNum,emPasswd,null);

        // token에 담은 검증을 위한 authenticationManager로 전달
        return authenticationManager.authenticate(authTocken);
    }

    // 로그인 성공시 실행하는 메소드(여기서 JWT 발급)

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,FilterChain chain,Authentication authentication){
        
        //UserDetails
        MemberDetails memberDetails = (MemberDetails)authentication.getPrincipal();

        String emName = memberDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(emName, role, 60*60*10L);

        response.addHeader("Authorization", "Bearer " + token);

    }

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,FilterChain chain){
        //로그인 실패시 401 응답 코드 반환
        response.setStatus(401);
    }

}
