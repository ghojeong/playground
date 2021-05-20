package com.oauth.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.oauth.annotation.LoginRequired;
import com.oauth.exception.TokenAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class LoginInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader("Authorization");
        if (((HandlerMethod) handler).hasMethodAnnotation(LoginRequired.class)) {
            authenticate(authorization, request);
        }
        return true;
    }

    // FIXME: Session 을 사용하지 않는 인증방법은 없을까?
    private void authenticate(String authorization, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String[] splitAuth = authorization.split(" ");
        String tokenType = splitAuth[0].toLowerCase();
        if (splitAuth.length < 1 || !tokenType.equals("bearer")) {
            throw new TokenAuthenticationException("잘못된 Authorization Header 입니다.");
        }
        String token = splitAuth[1];
        handleJwt(token, request);
        if (session.getAttribute(token) == null) {
            throw new TokenAuthenticationException("로그인하지 않은 유저입니다.");
        }
    }

    private void handleJwt(String token, HttpServletRequest request) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("jwtIssuer")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            String login = jwt.getClaim("login").asString();
            String name = jwt.getClaim("name").asString();
            logger.info("login:{}, name: {}", login, name);
            request.setAttribute("login", login);
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            throw new TokenAuthenticationException("잘못된 jwt 입니다.");
        }
    }
}
