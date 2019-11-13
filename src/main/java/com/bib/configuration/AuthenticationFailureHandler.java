package com.bib.configuration;

import java.io.IOException;
import javax.servlet.ServletException;
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
            AuthenticationException exception) throws IOException, ServletException {
        logger.debug("authentication info: [{}]", exception.getMessage());

    }
}
