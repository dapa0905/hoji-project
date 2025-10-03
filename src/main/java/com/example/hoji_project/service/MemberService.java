package com.example.hoji_project.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.example.hoji_project.model.JwtToken;
import com.example.hoji_project.model.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements MemberServiceImpl{
  
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final JwtTokenProvider jwtTokenProvider;
  
  @Override
  public JwtToken signIn(String name, String password) {
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, password);
    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
    JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
    return jwtToken;
  }

}
