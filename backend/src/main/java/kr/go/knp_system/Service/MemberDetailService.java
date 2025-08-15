package kr.go.knp_system.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.go.knp_system.Entity.KnpMember;
import kr.go.knp_system.Repository.LoginRepository;
import kr.go.knp_system.RequestDTO.MemberDetails;

@Service
public class MemberDetailService implements UserDetailsService{

    private final LoginRepository loginRepository;

    public MemberDetailService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emIdNum) throws UsernameNotFoundException {
        KnpMember user = loginRepository.findByEmIdNum(emIdNum)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + emIdNum));

        return new MemberDetails(user); // MemberDetails는 KnpMember 받도록
    }
    
}
