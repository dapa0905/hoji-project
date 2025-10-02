package com.example.hoji_project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import com.example.hoji_project.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
              .csrf(AbstractHttpConfigurer::disable) // CSRF(Cross-Site Request Forgery) 보호를 비활성화
              .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                      .requestMatchers("/**").permitAll() 
                      .anyRequest().authenticated() 
              )
              .logout(logout -> logout.logoutSuccessUrl("/")) 
              .oauth2Login(oauth2Login -> oauth2Login
                      .loginPage("/member/login")
                      .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                              .userService(customOAuth2UserService) 
                      )
              );
      return http.build();
  }

}
