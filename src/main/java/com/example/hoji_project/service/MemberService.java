package com.example.hoji_project.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.hoji_project.model.JwtToken;
import com.example.hoji_project.model.JwtTokenProvider;
import com.example.hoji_project.model.SignUpDTO;
import com.example.hoji_project.model.UserDTO;
import com.example.hoji_project.model.UserRole;
import com.example.hoji_project.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements MemberServiceImpl{
  
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final JwtTokenProvider jwtTokenProvider;
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  
  @Override
  public JwtToken signIn(String name, String password) {
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, password);
    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
    JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
    return jwtToken;
  }
  
  @Override
  public UserDTO signUp(SignUpDTO signUpDTO) {
    if(memberRepository.existsByEmail(signUpDTO.getEmail())) {
      throw new IllegalArgumentException("이미 사용중인 사용자 이릅입니다.");
    }
    String encodedPassword = passwordEncoder.encode(signUpDTO.getPassword());
    List<String>roles = new ArrayList<>();
    roles.add("User");
    signUpDTO.setUserRole(UserRole.USER);
    return UserDTO.toUserDTO(memberRepository.save(signUpDTO.toEntity(encodedPassword, null)));
  }

}
