package kr.go.knp_system.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.go.knp_system.Entity.KnpMember;
import kr.go.knp_system.Repository.LoginRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public Optional<KnpMember> findByEmIdNum(String emIdNum){
      return loginRepository.findByEmIdNum(emIdNum);
    }

    // public KnpMember save(KnpMember knpMember){
    //     return loginRepository.save(knpMember);
    // }
}
