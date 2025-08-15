package kr.go.knp_system.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JWTUtil {
    
    private SecretKey secretKey;

    public JWTUtil (@Value("${spring.jwt.secret}")String secret){
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    //검증
    public String getEmIdNum(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("emIdNum", String.class);   //토큰 검증으로 시크릿키 넣어서 토큰이 우리서버에서 생성되었는지? 키랑 맞는지 확인하고 builc타입리턴하고 클래임을 확인하고 페이로드 부분에서 특정 데이터는 get 을통해
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role",String.class);
    }

    public Boolean isExpired(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    // 토큰 생성

    public String createJwt(String emIdNum,String role, long expiredMs){

        return Jwts.builder()
        .claim("emIdNum",emIdNum)
        .claim("role",role)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis()))
        .signWith(secretKey)
        .compact();
    }
}
