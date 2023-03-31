package com.g31.demo.filter;

import com.g31.demo.common.SecurityConst;
import com.g31.demo.utils.JwtTokenUtils;
import io.jsonwebtoken.JwtException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: This class will act as a filter for all incoming HTTP requests.
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    // stringRedisTemplate is used for caching token information.
    private final StringRedisTemplate stringRedisTemplate;
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, StringRedisTemplate stringRedisTemplate) {
        super(authenticationManager);
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * Filter to validate and authenticate users based on a JSON Web Token (JWT) in HTTP requests.
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader(SecurityConst.TOKEN_HEADER);
        if(token == null || !token.startsWith(SecurityConst.TOKEN_PREFIX)){
            // If the token is not valid, it clears the security context
            // and the filter chain continues as normal.
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
            return;
        }

        // If the token is valid, it removes the prefix of the token
        // and retrieves the user authentication information from the token.
        String tokenValue = token.replace(SecurityConst.TOKEN_PREFIX, "");

        UsernamePasswordAuthenticationToken authentication = null;
        try{
            String previousToken = stringRedisTemplate.opsForValue().get(JwtTokenUtils.getId(tokenValue));

            // It checks if the current token is the same as the one stored in the cache.
            if (!token.equals(previousToken)) {

                // If the token is not the same, it clears the security context and the filter chain continues as normal.
                SecurityContextHolder.clearContext();
                chain.doFilter(request, response);
                return;
            }
            // If the token is valid and the current token is the same as the one stored in the cache,
            // the user authentication information is set in the security context.
            authentication = JwtTokenUtils.getAuthentication(tokenValue);
        } catch (JwtException e) {
            logger.error("Invalid jwt : " + e.getMessage());
        }

        // SecurityContextHolder is used for getting the user information after user's logging in.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }


}
