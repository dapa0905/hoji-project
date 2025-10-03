package com.example.hoji_project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.hoji_project.filter.JwtAuthenticationFilter;
import com.example.hoji_project.model.JwtTokenProvider;
import com.example.hoji_project.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final JwtTokenProvider jwtTokenProvider;
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
              .csrf(AbstractHttpConfigurer::disable)
              .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                              .requestMatchers("/**").permitAll()
                              .anyRequest().authenticated())
              .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
              .oauth2Login(oauth2Login -> oauth2Login
                              .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                              .userService(customOAuth2UserService))
              );
      return http.build();
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

}
