package com.g31.demo.common;

/**
 * @Description:
 */
public final class SecurityConst {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    // TODO: design a secret key
    public static final String JWT_SECRET_KEY = "";
    public static final String ROLE_CLAIMS = "rol";

    // System WHITELIST
    public static final String[] SYSTEM_WHITELIST = {
            "/auth/login",
            "/users/sign-up"
    };

    public static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html",
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs"
    };

    public static final String H2_CONSOLE = "/h2-console/**";
}
