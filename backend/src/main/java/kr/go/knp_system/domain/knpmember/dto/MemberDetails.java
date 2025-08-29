package kr.go.knp_system.domain.knpmember.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.go.knp_system.domain.knpmember.entity.KnpMember;



public class MemberDetails implements UserDetails{

    private final KnpMember knpMember;

    public MemberDetails(KnpMember knpMember){
        this.knpMember = knpMember;
    }

    @Override
    public String getPassword() {
        System.out.println("*************************"+knpMember.getEmPasswd());
        return knpMember.getEmPasswd();
    }

    @Override
    public String getUsername() {
        return knpMember.getEmIdNum();
    }
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

     @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

}