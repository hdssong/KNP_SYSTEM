package kr.go.knp_system.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import kr.go.knp_system.Entity.KnpMember;
import kr.go.knp_system.Repository.LoginRepository;

@Service
public class LoginService {

  private final AuthenticationManager authenticationManager = null;
  private final LoginRepository loginRepository;

      @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
  // public boolean authenticate(String emIdNum, String emPasswd) {
  //   try {
  //     authenticationManager.authenticate(
  //         new UsernamePasswordAuthenticationToken(emIdNum, emPasswd));

  //     return true;
  //   } catch (AuthenticationException e) {
  //     return false;

  //   }
  // }

  public Optional<KnpMember> findByEmIdNum(String emIdNum) {
    return loginRepository.findByEmIdNum(emIdNum);
  }

  // 전체 사용자 조회 (테스트용)
    public List<KnpMember> findAll() {
        return loginRepository.findAll();
    }
}
