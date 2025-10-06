package com.example.hoji_project.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {
  private String name;
  private String email;
  private String password;
  private String picture;
  private String provider;
  private String providerId;
  
  
  public static UserDTO toUserDTO(UserEntity userEntity) {
    return UserDTO.builder()
        .name(userEntity.getName())
        .email(userEntity.getEmail())
        .password(userEntity.getPassword())
        .picture(userEntity.getPicture())
        .provider(userEntity.getProvider())
        .providerId(userEntity.getProviderId()).build();

  }
  
  //OAuth2 로그인 전용
  public UserDTO(UserEntity userEntity) {
    this.setEmail(userEntity.getEmail());
    this.setName(userEntity.getName());
    this.setPassword(userEntity.getPassword());
    this.setPicture(userEntity.getPicture());
    this.setProvider(userEntity.getProvider());
    this.setProviderId(userEntity.getProviderId());
  }
  
  

}
