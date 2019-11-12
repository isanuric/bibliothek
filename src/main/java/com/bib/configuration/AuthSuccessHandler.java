package com.bib.configuration;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        Set<String> definedRoles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (definedRoles.contains(SecurityConfig.ADMIN_ROLE)) {
            response.sendRedirect("/admin");
        } else {
            response.sendRedirect("/user");
        }

    }
}
