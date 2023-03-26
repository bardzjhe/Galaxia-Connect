package com.g31.demo.config;

import com.g31.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

/**
 * @Description: Implements authentication and authorization.
 */

@Configuration
@EnableWebSecurity  // Spring Security
public class WebSecurityConfig {

    @Autowired(required = false)
    private LoginSuccessHandler successHandler;

    @Bean // loads user-specific data that is used for authentication.
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }

    @Bean // encodes and validates passwords using the BCrypt algorithm.
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * @return an instance of DaoAuthenticationProvider configured to use the previously
     * defined UserDetailsService() and PasswordEncoder() implementations.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    /**
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


//        //Public pages:
//        http.authorizeHttpRequests(requests -> requests.antMatchers("/").permitAll());
//        http.authorizeHttpRequests(requests -> requests.antMatchers("/login").permitAll());
////        http.authorizeHttpRequests(requests -> requests.antMatchers("/loginError").permitAll());
//        http.authorizeHttpRequests(requests -> requests.antMatchers("/homepage").permitAll());
//
//
//        //Form login:
//        http.formLogin(login -> login.loginPage("/login"));
//        http.formLogin(login -> login.usernameParameter("username"));
//        http.formLogin(login -> login.passwordParameter("password"));
//        http.formLogin(login -> login.defaultSuccessUrl("/"));
//        http.formLogin(login -> login.failureUrl("/login?error=true"));
//        http.formLogin(login -> login.successHandler(successHandler));

        //Private pages:
//        http.authorizeHttpRequests(requests -> requests.antMatchers("/user/dashboard").hasAnyAuthority("USER"));

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
        http.csrf().disable().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
//                .successHandler(successHandler)
                .usernameParameter("email")
                .passwordParameter("password").and();
//        // logout
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");


        http.authenticationProvider(authenticationProvider());
        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }



}
