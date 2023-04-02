package com.g31.demo.common;

/**
 * @Description:
 */
public final class SecurityConst {
<<<<<<< Updated upstream
=======

    public static final String TOKEN_TYPE = "JWT";

    // the expiration period is 20 minutes if rememberMe is false.
    public static final long EXPIRATION_FALSE_REMEMBER = 20 * 60L;

    // the expiration period is 7 days if rememberMe is true.
    public static final long EXPIRATION_TRUE_REMEMBER = 60 * 60 * 24 * 7L;
>>>>>>> Stashed changes
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
