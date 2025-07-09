package kr.go.knp_system.Service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import kr.go.knp_system.Entity.KnpMember;
import kr.go.knp_system.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

    // private final MemberRepository memberRepository;

    // public void registerMember(KnpMember knpmember) throws IllegalAccessException{

    //     // if (memberRepository.existsByName(knpmember.getName())) {
    //     //     throw new IllegalAccessException("이미 존재하는 사용자 입니다.");
            
    //     // }

    //     memberRepository.save(knpmember);
    // }

    // public KnpMember findByName(String name){
    //     return memberRepository.findByName(name)
    //         .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다/"));

    // }
}
