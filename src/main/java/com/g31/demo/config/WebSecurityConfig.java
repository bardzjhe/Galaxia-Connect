package com.g31.demo.config;

import com.g31.demo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

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

//        http.csrf().disable()
//                .authorizeRequests()


//        //Public pages:
        http.authorizeRequests().antMatchers("/", "/login", "/loginError").permitAll().
                antMatchers("/account/**").hasAnyAuthority("USER")
                .and()
                .csrf().disable()
                .formLogin();

        //Form login:
        http.formLogin(login -> login.loginPage("/login"));
        http.formLogin(login -> login.usernameParameter("username"));
        http.formLogin(login -> login.passwordParameter("password"));
        http.formLogin(login -> login.defaultSuccessUrl("/"));
        http.formLogin(login -> login.failureUrl("/login?error=true"));
//        http.formLogin(login -> login.successHandler(successHandler));

//        Private pages:
        http.authorizeHttpRequests(requests -> requests.antMatchers("/user/dashboard").hasAnyAuthority("USER"));

        http.authorizeRequests()
                // URL matching for accessibility
                // All people can access
                .antMatchers( "/", "/register", "/login").permitAll()
                // Only admin can access
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                // Only user can access
                .antMatchers("/account/**").hasAnyAuthority("USER")
                .anyRequest().authenticated();
        // form login
//        http
//                .loginPage("/login")
////                .failureUrl("/login?error=true")
//                .successHandler(successHandler)
//                .usernameParameter("email")
//                .passwordParameter("password").and();
////        // logout
//        http.logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/")
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/access-denied");
//
//
//        http.authenticationProvider(authenticationProvider());
//        http.headers().frameOptions().sameOrigin();
//
//        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/chatroom","/user");
        registry.setUserDestinationPrefix("/user");
    }

}
