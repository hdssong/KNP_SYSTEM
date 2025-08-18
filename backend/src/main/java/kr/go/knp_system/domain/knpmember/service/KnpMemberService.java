package kr.go.knp_system.domain.knpmember.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.go.knp_system.domain.jwt.service.JwtService;
import kr.go.knp_system.domain.knpmember.dto.LoginRequestDto;
import kr.go.knp_system.domain.knpmember.dto.MemberDetails;
import kr.go.knp_system.domain.knpmember.entity.KnpMember;
import kr.go.knp_system.domain.knpmember.repository.LoginRepository;

@Service
public class KnpMemberService implements UserDetailsService {

    private final LoginRepository loginRepository;
    private final JwtService jwtService;

    public KnpMemberService(LoginRepository loginRepository, JwtService jwtService) {
        this.loginRepository = loginRepository;
        this.jwtService = jwtService;
    }

    // 자체 로그인
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String emIdNum) throws UsernameNotFoundException {
        
        KnpMember user = loginRepository.findByEmIdNum(emIdNum)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + emIdNum));

        System.out.println(user);
        return new MemberDetails(user); // MemberDetails는 KnpMember 받도록
    }

    @Transactional
    public void deleteUser(LoginRequestDto dto) throws AccessDeniedException {

        // 본인 및 어드민만 삭제 가능 검증
        //SecurityContext context = SecurityContextHolder.getContext();
        
        //String sessionUsername = context.getAuthentication().getName();
        // String sessionRole =
        // context.getAuthentication().getAuthorities().iterator().next().getAuthority();

        //boolean isOwner = sessionUsername.equals(dto.getEmIdNum());
        // boolean isAdmin = sessionRole.equals("ROLE_"+UserRoleType.ADMIN.name());

        // if (!isOwner && !isAdmin) {
        // throw new AccessDeniedException("본인 혹은 관리자만 삭제할 수 있습니다.");
        // }

        // 유저 제거
        loginRepository.deleteByEmIdNum(dto.getEmIdNum());

        // Refresh 토큰 제거
        jwtService.removeRefreshUser(dto.getEmIdNum());
    }

}
