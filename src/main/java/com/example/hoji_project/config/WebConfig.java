package com.example.hoji_project.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.hoji_project.resolver.LoginuserArgumentResolver;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configurable
public class WebConfig implements WebMvcConfigurer {

  private final LoginuserArgumentResolver argumentResolver;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(argumentResolver);
  }

}
