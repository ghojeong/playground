package com.oauth.controller;

import com.oauth.annotation.LoginRequired;
import com.oauth.dto.*;
import com.oauth.util.TokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api")
public class OauthController {
    private final TokenUtil tokenUtil;

    public OauthController(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @GetMapping("/hello")
    @LoginRequired
    public MessageResponse getHello() {
        return new MessageResponse("안녕하세요! 로그인 한 유저는 언제나 환영합니다!");
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest authRequest, HttpServletRequest request) {
        String code = authRequest.getCode();
        RestTemplate restTemplate = new RestTemplate();

        GithubAccessTokenResponse githubAccessTokenResponse = tokenUtil.getAccessToken(code, restTemplate);
        String accessToken = githubAccessTokenResponse.getAccessToken();

        UserResponse user = tokenUtil.getUserFromGitHub(accessToken, restTemplate);
        String token = tokenUtil.createJwt(user);

        HttpSession session = request.getSession();
        session.setAttribute(token, githubAccessTokenResponse);
        return ResponseEntity.status(CREATED).body(new AuthResponse(token));
    }
}
