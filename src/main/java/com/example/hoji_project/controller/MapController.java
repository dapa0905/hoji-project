package com.example.hoji_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
  
  @GetMapping("/map")
  public String MapMainPage() {
    return "map/map";
  }

}
