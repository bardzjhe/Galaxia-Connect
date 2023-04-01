package com.g31.demo.config;

import com.g31.demo.common.SecurityConst;
import com.g31.demo.exception.JwtAccessDeniedException;
import com.g31.demo.filter.JwtAuthorizationFilter;
import com.g31.demo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Arrays;

import static java.util.Collections.singletonList;

/**
 * @Description: Implements authentication and authorization.
 */

// TODO: It's the most important class for security issues but not yet finished.
@Configuration
@EnableWebSecurity  // Spring Security
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSecurityConfig implements WebSocketMessageBrokerConfigurer{


    private final StringRedisTemplate stringRedisTemplate;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean // encodes and validates passwords using the BCrypt algorithm.
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public void config(HttpSecurity http) throws Exception {

        http.cors(Customizer.withDefaults()).csrf().disable() // disable csrf for testing purpose.
                .authorizeRequests()
                //Public pages:
//                .antMatchers(SecurityConst.H2_CONSOLE).permitAll()
                .antMatchers(SecurityConst.SWAGGER_WHITELIST).permitAll()
                .antMatchers(SecurityConst.SYSTEM_WHITELIST).permitAll()
                .antMatchers(HttpMethod.POST,"/users/sign-up").permitAll()
                //Authentication
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager((http.getSharedObject(AuthenticationConfiguration.class)))
                        , stringRedisTemplate))
                // TODO: If it's stateful or stateless.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().accessDeniedHandler(new JwtAccessDeniedException());

        http.headers().frameOptions().disable();
        http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }

//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.setApplicationDestinationPrefixes("/app");
//        registry.enableSimpleBroker("/chatroom","/user");
//        registry.setUserDestinationPrefix("/user");
//    }

    /**
     * @author shuang kou
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(singletonList("*"));
        // configuration.setAllowedOriginPatterns(singletonList("*"));
        configuration.setAllowedHeaders(singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        configuration.setExposedHeaders(singletonList(SecurityConst.TOKEN_HEADER));
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
