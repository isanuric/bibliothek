package com.bib.configuration;

import com.hazelcast.config.CacheSimpleConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import io.github.bucket4j.grid.GridBucketState;
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

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private DataSource dataSource;

    private static final String LOGIN = "/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/home", "/about", "/toolbar/**", "/book/**", "/user/add", "/author/**", "/pic/**")
                .permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage(LOGIN)
                .loginProcessingUrl(LOGIN)
                .successHandler(authSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()

                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)

                .permitAll()

                .and()
                .exceptionHandling().accessDeniedPage(LOGIN);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
//                .usersByUsernameQuery("select userId,password,enabled from bib_users where userId = ?")
//                .authoritiesByUsernameQuery("select userId,authority from authorities where userId = ?");
                .usersByUsernameQuery("select username,password,enabled from bibdb.members where username = ?")
                .authoritiesByUsernameQuery("select username_ref,role from authorities where username_ref = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public IMap<String, GridBucketState>cache() {
        Config config = new Config();
        config.setLiteMember(false);
        CacheSimpleConfig cacheConfig = new CacheSimpleConfig();
        cacheConfig.setName("buckets");
        config.addCacheConfig(cacheConfig);

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        IMap<String, GridBucketState> map = hazelcastInstance.getMap("my-distributed-map");
        return map;

//        ICacheManager cacheManager = hazelcastInstance.getCacheManager();
//        Cache<String, GridBucketState> cache = cacheManager.getCache("buckets");
//        return cache;
    }

}
