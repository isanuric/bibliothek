package com.bib.configuration;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class LogoutSuccessHandler implements
        org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    private static Logger logger = LoggerFactory.getLogger(Logger.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        try {
            if (!isEmpty(authentication) && !isEmpty(authentication.getDetails())) {
                request.getSession().invalidate();
            }
        } catch (Exception e) {
           logger.debug("exeption: [{}]", e.getMessage());
        }

        response.setStatus(HttpServletResponse.SC_OK);
        logger.info("Success logout. Redirect to home page.");
        response.sendRedirect("/home");

    }
}
