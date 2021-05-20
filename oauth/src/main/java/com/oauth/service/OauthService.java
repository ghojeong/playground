package com.oauth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.oauth.dto.GithubAccessTokenRequest;
import com.oauth.dto.GithubAccessTokenResponse;
import com.oauth.dto.UserResponse;
import com.oauth.exception.TokenCreationException;
import com.oauth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OauthService {
    private static final String GITHUB_ACCESS_TOKEN_URI = "https://github.com/login/oauth/access_token";
    private static final String GITHUB_USER_URI = "https://api.github.com/user";
    private static RestTemplate restTemplate = new RestTemplate();

    @Value("${github.client.id}")
    private String CLIENT_ID;

    @Value("${github.client.secret}")
    private String CLIENT_SECRET;

    public GithubAccessTokenResponse getAccessToken(String code) {
        RequestEntity<GithubAccessTokenRequest> request = RequestEntity
                .post(GITHUB_ACCESS_TOKEN_URI)
                .header("Accept", "application/json")
                .body(new GithubAccessTokenRequest(CLIENT_ID, CLIENT_SECRET, code));

        ResponseEntity<GithubAccessTokenResponse> response = restTemplate
                .exchange(request, GithubAccessTokenResponse.class);

        return Optional.ofNullable(response.getBody())
                .orElseThrow(() -> new TokenCreationException("Access Token 획득 실패"));
    }

    public UserResponse getUserFromGitHub(String accessToken) {
        RequestEntity<Void> request = RequestEntity
                .get(GITHUB_USER_URI)
                .header("Accept", "application/json")
                .header("Authorization", "token " + accessToken)
                .build();

        ResponseEntity<UserResponse> response = restTemplate
                .exchange(request, UserResponse.class);

        return Optional.ofNullable(response.getBody())
                .orElseThrow(() -> new TokenCreationException("유저 정보 획득 실패"));
    }

    public String createJwt(UserResponse user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtUtil.getSecret());
            return JWT.create()
                    .withIssuer(JwtUtil.getIssuer())
                    .withClaim("login", user.getLogin())
                    .withClaim("name", user.getName())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new TokenCreationException("JWT 생성 실패");
        }
    }
}
