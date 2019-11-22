package com.bib.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureHandler implements
        org.springframework.security.web.authentication.AuthenticationFailureHandler {

    private static Logger logger = LoggerFactory.getLogger(Logger.class);


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) {
        logger.info("authentication info: [{}]", exception.getMessage());

    }
}
