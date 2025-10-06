package com.example.hoji_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.hoji_project.model.JwtToken;
import com.example.hoji_project.model.SignUpDTO;
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
  
  @PostMapping("/member/login")
  public JwtToken signIn(@ModelAttribute UserDTO userDTO) {
    log.info("UserDto:{}", userDTO.getEmail());
    log.info("UserDto:{}", userDTO.getPassword());
    JwtToken jwtToken = memberService.signIn(userDTO.getEmail(), userDTO.getPassword());
    return jwtToken;
  }
  
  @PostMapping("/member/register")
  public ResponseEntity<UserDTO> signUp(@ModelAttribute SignUpDTO signUpDTO) {
    log.info("SignUpDTOEmail :{}", signUpDTO.getEmail());
    log.info("SignUpDTOName :{}", signUpDTO.getName());
    log.info("SignUpDTOPassword :{}", signUpDTO.getPassword());
    UserDTO savedUserDTO = memberService.signUp(signUpDTO);
    return ResponseEntity.ok(savedUserDTO);
  }
  

}
