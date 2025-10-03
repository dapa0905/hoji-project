package com.example.hoji_project.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDTO {

  private String name;
  private String password;
  private String email;
  private String picture;
  private List<String> roles = new ArrayList<>();
  
  public UserEntity toEntity(String encodedPassword, List<String> roles) {
    return UserEntity.builder()
        .name(name)
        .password(encodedPassword)
        .email(email)
        .picture(picture)
        .build();
  }
}
