package com.example.hoji_project.service;

import com.example.hoji_project.model.JwtToken;
import com.example.hoji_project.model.SignUpDTO;
import com.example.hoji_project.model.UserDTO;

public interface MemberServiceImpl {
  
  // 토큰 로그인 방식
  JwtToken signIn(String name, String password);
  
  // 회원 가입
  UserDTO signUp(SignUpDTO signUpDTO);
  
  

}
