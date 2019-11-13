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

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        Set<String> definedRoles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (definedRoles.contains(SecurityConfig.ROLE_ADMIN)) {
            logger.info("found admin role.");
            response.sendRedirect("/admin");
        } else if (definedRoles.contains(SecurityConfig.ROLE_USER)) {
            logger.info("found user role.");
            response.sendRedirect("/user");
        }

    }

//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//            HttpServletResponse response, Authentication authentication)
//            throws IOException {
//
//        handle(request, response, authentication);
//        clearAuthenticationAttributes(request);
//    }
//
//    protected void handle(HttpServletRequest request,
//            HttpServletResponse response, Authentication authentication)
//            throws IOException {
//
//        String targetUrl = determineTargetUrl(authentication);
//
//        if (response.isCommitted()) {
//            logger.debug(
//                    "Response has already been committed. Unable to redirect to "
//                            + targetUrl);
//            return;
//        }
//
//        redirectStrategy.sendRedirect(request, response, targetUrl);
//    }
//
//    protected String determineTargetUrl(Authentication authentication) {
//        boolean isUser = false;
//        boolean isAdmin = false;
//        Collection<? extends GrantedAuthority> authorities
//                = authentication.getAuthorities();
//        for (GrantedAuthority grantedAuthority : authorities) {
//            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
//                isUser = true;
//                break;
//            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
//                isAdmin = true;
//                break;
//            }
//        }
//
//        if (isUser) {
//            return "/user";
//        } else if (isAdmin) {
//            return "/admin";
//        } else {
//            throw new IllegalStateException();
//        }
//    }
//
//    protected void clearAuthenticationAttributes(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return;
//        }
//        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//    }
//
//    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
//        this.redirectStrategy = redirectStrategy;
//    }
//    protected RedirectStrategy getRedirectStrategy() {
//        return redirectStrategy;
//    }
}
