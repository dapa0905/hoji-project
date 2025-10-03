package com.example.hoji_project.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable {
  private String name;
  private String email;
  private String password;
  private String picture;
  private String provider;
  private String providerId;

  public UserDTO(UserEntity userEntity) {
    this.name = userEntity.getName();
    this.email = userEntity.getEmail();
    this.password = userEntity.getPassword();
    this.picture = userEntity.getPicture();
    this.provider = userEntity.getProvider();
    this.providerId = userEntity.getProviderId();
  }

}
