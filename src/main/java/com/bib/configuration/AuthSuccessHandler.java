package com.bib.configuration;

import java.io.IOException;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private static Logger logger = LoggerFactory.getLogger(Logger.class);

    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        Set<String> definedRoles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (definedRoles.contains(ROLE_ADMIN)) {
            logger.info("found admin role.");
            response.sendRedirect("/admin");
        } else if (definedRoles.contains(ROLE_USER)) {
            logger.info("found user role.");
            response.sendRedirect("/user");
        }
    }
}
