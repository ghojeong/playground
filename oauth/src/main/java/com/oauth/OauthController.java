package com.oauth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.oauth.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/api")
public class OauthController {
    private static final String GITHUB_ACCESS_TOKEN_URI = "https://github.com/login/oauth/access_token";
    private static final String GITHUB_USER_URI = "https://api.github.com/user";

    @Value("${github.client.id}")
    private String CLIENT_ID;

    @Value("${github.client.secret}")
    private String CLIENT_SECRET;

    // FIXME: jwt 와 accessCode 를 어떻게 다루어야 할까?
    private final Map<String, GithubAccessTokenResponse> loggedInUser = new ConcurrentHashMap<>();

    @GetMapping("/hello")
    public ResponseEntity<String> getHello(String token) {
        if (loggedInUser.containsKey(token)) {
            return ResponseEntity.ok().body("안녕하세요! 로그인된 유저이군요!");
        }
        return ResponseEntity.status(UNAUTHORIZED).body("인증되지 않은 유저입니다.");
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest authRequest) {
        String code = authRequest.getCode();
        RestTemplate restTemplate = new RestTemplate();
        GithubAccessTokenResponse githubAccessTokenResponse = getAccessToken(code, restTemplate);
        String accessToken = githubAccessTokenResponse.getAccessToken();
        UserResponse user = getUserFromGitHub(accessToken, restTemplate);

        String token = createJwt(user);
        loggedInUser.put(token, githubAccessTokenResponse);
        return ResponseEntity.status(CREATED).body(new AuthResponse(token));
    }

    private GithubAccessTokenResponse getAccessToken(String code, RestTemplate restTemplate) {
        RequestEntity<GithubAccessTokenRequest> request = RequestEntity
                .post(GITHUB_ACCESS_TOKEN_URI)
                .header("Accept", "application/json")
                .body(new GithubAccessTokenRequest(CLIENT_ID, CLIENT_SECRET, code));

        ResponseEntity<GithubAccessTokenResponse> response = restTemplate
                .exchange(request, GithubAccessTokenResponse.class);

        // FIXME: 커스텀 Exception 으로 정의 필요
        return Optional.ofNullable(response.getBody())
                .orElseThrow(() -> new RuntimeException("Access Token 획득 실패"));
    }

    private UserResponse getUserFromGitHub(String accessToken, RestTemplate gitHubRequest) {
        RequestEntity<Void> request = RequestEntity
                .get(GITHUB_USER_URI)
                .header("Accept", "application/json")
                .header("Authorization", "token " + accessToken)
                .build();

        ResponseEntity<UserResponse> response = gitHubRequest
                .exchange(request, UserResponse.class);

        // FIXME: 커스텀 Exception 으로 정의 필요
        return Optional.ofNullable(response.getBody())
                .orElseThrow(() -> new RuntimeException("유저 정보 획득 실패"));
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
            throw new RuntimeException("JWT 생성 실패");
        }
    }
}
