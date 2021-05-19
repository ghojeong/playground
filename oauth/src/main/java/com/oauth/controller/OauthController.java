package com.oauth.controller;

import com.oauth.annotation.LoginRequired;
import com.oauth.dto.*;
import com.oauth.service.OauthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api")
public class OauthController {
    private final OauthService oauthService;

    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/hello")
    @LoginRequired
    public MessageResponse getHello() {
        return new MessageResponse("안녕하세요! 로그인 한 유저는 언제나 환영합니다!");
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest authRequest, HttpServletRequest request) {
        String code = authRequest.getCode();

        GithubAccessTokenResponse githubAccessTokenResponse = oauthService.getAccessToken(code);
        String accessToken = githubAccessTokenResponse.getAccessToken();

        UserResponse user = oauthService.getUserFromGitHub(accessToken);
        String jwt = oauthService.createJwt(user);

        HttpSession session = request.getSession();
        session.setAttribute(jwt, githubAccessTokenResponse);
        return ResponseEntity.status(CREATED).body(new AuthResponse(jwt));
    }
}
