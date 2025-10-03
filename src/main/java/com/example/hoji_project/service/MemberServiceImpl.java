package com.example.hoji_project.service;

import com.example.hoji_project.model.JwtToken;

public interface MemberServiceImpl {
  
  JwtToken signIn(String name, String password);
  
  

}
