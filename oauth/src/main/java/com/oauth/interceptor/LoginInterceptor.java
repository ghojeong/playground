package com.oauth.interceptor;

import com.oauth.annotation.LoginRequired;
import com.oauth.exception.TokenAuthenticationException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader("Authorization");
        if (((HandlerMethod) handler).hasMethodAnnotation(LoginRequired.class)) {
            authenticate(authorization, request.getSession());
        }
        return true;
    }

    // FIXME: Session 을 사용하지 않는 인증방법은 없을까?
    private void authenticate(String authorization, HttpSession session) {
        String[] splitAuth = authorization.split(" ");
        if (splitAuth.length < 1 || !splitAuth[0].equals("Bearer")) {
            throw new TokenAuthenticationException("잘못된 Authorization Header 입니다.");
        }
        String token = splitAuth[1];
        if (session.getAttribute(token) == null) {
            throw new TokenAuthenticationException("로그인하지 않은 유저입니다.");
        }
    }
}
