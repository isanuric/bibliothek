package com.bib.configuration;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    protected static final String ROLE_USER = "ROLE_USER";
    protected static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/home", "/admin","/about", "/book/**", "/user/add", "/pic/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole(ROLE_ADMIN)
                .antMatchers("/user/**").hasAnyRole(ROLE_USER, ROLE_ADMIN)
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
//                .defaultSuccessUrl("/user")
                .loginProcessingUrl("/login")
                .successHandler(authSuccessHandler)
                .permitAll()

                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();
//                .and()
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select userId,password,enabled from bib_users where userId = ?")
                .authoritiesByUsernameQuery("select userId,authority from authorities where userId = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean public LogoutSuccessHandler logoutSuccessHandler() {
//       return new LogoutSuccessHandler();
//    }

}
