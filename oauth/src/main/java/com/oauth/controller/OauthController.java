package com.oauth.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.oauth.annotation.LoginRequired;
import com.oauth.dto.*;
import com.oauth.exception.TokenCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api")
public class OauthController {
    private static final String GITHUB_ACCESS_TOKEN_URI = "https://github.com/login/oauth/access_token";
    private static final String GITHUB_USER_URI = "https://api.github.com/user";

    @Value("${github.client.id}")
    private String CLIENT_ID;

    @Value("${github.client.secret}")
    private String CLIENT_SECRET;

    @GetMapping("/hello")
    @LoginRequired
    public MessageResponse getHello() {
        return new MessageResponse("안녕하세요! 로그인 한 유저는 언제나 환영합니다!");
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest authRequest, HttpServletRequest request) {
        String code = authRequest.getCode();
        RestTemplate restTemplate = new RestTemplate();
        GithubAccessTokenResponse githubAccessTokenResponse = getAccessToken(code, restTemplate);
        String accessToken = githubAccessTokenResponse.getAccessToken();
        UserResponse user = getUserFromGitHub(accessToken, restTemplate);

        String token = createJwt(user);

        HttpSession session = request.getSession();
        session.setAttribute(token, githubAccessTokenResponse);
        return ResponseEntity.status(CREATED).body(new AuthResponse(token));
    }

    private GithubAccessTokenResponse getAccessToken(String code, RestTemplate restTemplate) {
        RequestEntity<GithubAccessTokenRequest> request = RequestEntity
                .post(GITHUB_ACCESS_TOKEN_URI)
                .header("Accept", "application/json")
                .body(new GithubAccessTokenRequest(CLIENT_ID, CLIENT_SECRET, code));

        ResponseEntity<GithubAccessTokenResponse> response = restTemplate
                .exchange(request, GithubAccessTokenResponse.class);

        return Optional.ofNullable(response.getBody())
                .orElseThrow(() -> new TokenCreationException("Access Token 획득 실패"));
    }

    private UserResponse getUserFromGitHub(String accessToken, RestTemplate gitHubRequest) {
        RequestEntity<Void> request = RequestEntity
                .get(GITHUB_USER_URI)
                .header("Accept", "application/json")
                .header("Authorization", "token " + accessToken)
                .build();

        ResponseEntity<UserResponse> response = gitHubRequest
                .exchange(request, UserResponse.class);

        return Optional.ofNullable(response.getBody())
                .orElseThrow(() -> new TokenCreationException("유저 정보 획득 실패"));
    }

    private String createJwt(UserResponse user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withClaim("login", user.getLogin())
                    .withClaim("name", user.getName())
                    .withIssuer("jwtTest")
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new TokenCreationException("JWT 생성 실패");
        }
    }
}