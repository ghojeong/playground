package com.oauth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.oauth.dto.UserDto;
import com.oauth.exception.JwtException;

public class JwtUtil {
    private static String JWT_SECRET = "jwtSecret";
    private static String JWT_ISSUER = "jwtIssuer";
    private static String USER_LOGIN = "login";
    private static String USER_NAME = "name";

    private JwtUtil() {}

    public static String createJwt(UserDto user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withClaim(USER_LOGIN, user.getLogin())
                    .withClaim(USER_NAME, user.getName())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JwtException("JWT 생성 실패");
        }
    }

    public static UserDto decodeJwt(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JWT_ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            String login = jwt.getClaim(USER_LOGIN).asString();
            String name = jwt.getClaim(USER_NAME).asString();
            return new UserDto(login, name);
        } catch (JWTVerificationException exception) {
            throw new JwtException("잘못된 jwt 입니다.");
        }
    }
}
