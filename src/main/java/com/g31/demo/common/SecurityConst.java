package com.g31.demo.common;

/**
 * @description Constant variables.
 */
public final class SecurityConst {

    /**
     * key for role (user, admin)
     **/
    public static final String ROLE_CLAIMS = "rol";

    /**
     * if rememberMe is false, the expiration time is 1 hour
     */
    public static final long EXPIRATION = 60 * 60L;

    /**
     * rememberMe is true, the expiration time is 7 days.
     */
    public static final long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7L;

    /**
     * The JWT signing key is hardcoded into the application code and should be stored in an environment variable or .properties file.
     */
    public static final String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    // Swagger WHITELIST
    public static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html",
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/webjars/**",
            //knife4j
            "/doc.html",
    };

    public static final String H2_CONSOLE = "/h2-console/**";

    // System WHITELIST
    public static final String[] SYSTEM_WHITELIST = {
            "/auth/login",
            "/users/sign-up"
    };

    private SecurityConst() {
    }

}
