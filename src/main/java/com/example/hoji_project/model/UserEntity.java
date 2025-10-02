package com.example.hoji_project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column
  private String picture;

  @Column
  private String provider;

  @Column
  private String providerId;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserRole userRole;
  
  @Builder
  public UserEntity(String name, String email, String picture, String provider, String providerId,
      UserRole userRole) {
    this.name = name;
    this.email = email;
    this.picture = picture;
    this.provider = provider;
    this.providerId = providerId;
    this.userRole = userRole;
  }

  public UserEntity() {

  }

  public UserEntity update(String name, String picture) {
    this.name = name;
    this.picture = picture;
    return this;
  }
  
  public String getRoleKey() {
    return this.userRole.getKey();
  }



}
