package com.oauth.util;

public class JwtUtil {
    private JwtUtil() {}

    public static String getSecret() {
        return "jwtSecret";
    }

    public static String getIssuer() {
        return "jwtIssuer";
    }
}
