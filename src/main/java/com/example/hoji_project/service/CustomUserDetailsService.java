package com.example.hoji_project.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.hoji_project.model.UserEntity;
import com.example.hoji_project.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
  
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    return memberRepository.findByName(name)
        .map(this::createUserDetails)
        .orElseThrow(()-> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
  }
  
  private UserDetails createUserDetails(UserEntity user) {
    return User.builder()
        .username(user.getEmail())
        .password(passwordEncoder.encode(user.getPassword()))
        .roles(user.getRoles().toArray(new String[0]))
        .build();
  }

}
