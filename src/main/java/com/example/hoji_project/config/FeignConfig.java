package com.example.hoji_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Logger;
import feign.Request;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {
  @Bean
  public Logger.Level feignLoggerLevel() {
      return Logger.Level.FULL;
  }

  @Bean
  public ErrorDecoder errorDecoder() {
      return new CustomFeignErrorDecorder();
  }
  
  @Bean
  public Request.Options options() {
      return new Request.Options();
  }
}