package com.example.hoji_project.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.example.hoji_project.annotation.LoginAnnotation.LoginUser;
import com.example.hoji_project.model.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LoginuserArgumentResolver implements HandlerMethodArgumentResolver{
  
  private final HttpSession httpSession;
  
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
    boolean isUserClass = UserDTO.class.equals(parameter.getParameterType());
    return isLoginUserAnnotation && isUserClass;
  }
  
  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
    HttpSession session = request.getSession(false);
    if (session == null) {
      return null;
    }
    return session.getAttribute("user");
  }
  

}
