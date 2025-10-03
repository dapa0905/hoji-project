package com.example.hoji_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.hoji_project.model.JwtToken;
import com.example.hoji_project.model.UserDTO;
import com.example.hoji_project.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/member/register")
  public String MemberRegisterPage() {
    return "member/register";
  }
  
  @PostMapping("/member/register")
  public JwtToken signIn(@ModelAttribute UserDTO userDTO) {
    JwtToken jwtToken = memberService.signIn(userDTO.getEmail(), userDTO.getPassword());
    return jwtToken;
  }

}
