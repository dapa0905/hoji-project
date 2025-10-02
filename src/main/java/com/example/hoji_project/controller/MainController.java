package com.example.hoji_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
  
  @GetMapping("/")
  public String loginPage() {
    return "index";
  }
  
  @GetMapping("/member/login")
  public String MemberloginPage() {
    return "member/login";
  }

}
