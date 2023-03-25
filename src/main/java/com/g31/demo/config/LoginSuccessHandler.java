package com.g31.demo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description: The code is about how to register view controllers with the Spring MVC framework.
 * This is done using the registry object, which is an instance of the ViewControllerRegistry class in Spring MVC.
 */
@Configuration
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    /**
     * It is responsible for redirecting the user to the appropriate URL after a successful login.
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     */
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response,
                          Authentication authentication) throws IOException{
        String targetUrl = determineTargetUrl(authentication);
        if(response.isCommitted()) return;
        // If the response has not been committed, it creates an instance of DefaultRedirectStrategy,
        // which is a class that implements the RedirectStrategy interface, and is used to perform the actual redirect.
//        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//        redirectStrategy.sendRedirect(request, response, targetUrl);
//        // testing code
//        System.out.println("hello, redirecting...");
        return;
    }

    private String determineTargetUrl(Authentication authentication) {
        // if the user fails to authenticate, they will be redirected to
        // the login page with an error message.
        String url = "/login?error=true";

        // gets the user's granted authorities using the getAuthorities() function,
        // which returns a collection of objects that implement the GrantedAuthority interface.
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();

        // loops through the granted authorities collection.
        for(GrantedAuthority a : authorities){
            roles.add(a.getAuthority());
        }

        // checks if the roles ArrayList contains the string "ADMIN".
        // If it does, the url variable is updated to "/admin/dashboard".
        if(roles.contains("ADMIN")){
            url = "/admin/dashboard";

        }else if(roles.contains("USER")) { // If user, the url variable is updated to "/dashboard".
            url = "/dashboard";
        }
        return url;
    }
}
