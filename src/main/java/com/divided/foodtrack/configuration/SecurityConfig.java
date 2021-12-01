package com.divided.foodtrack.configuration;


import com.divided.foodtrack.security.jwt.JwtConfigurer;
import com.divided.foodtrack.security.jwt.JwtTokenProvider;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    JwtTokenProvider jwtTokenProvider;

    static String USER_ENDPOINT = "/user/**";
    static String ADMIN_ENDPOINT = "/admin/**";

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable().cors().
                and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                .anyRequest().permitAll()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));

        http.headers()
                .frameOptions()
                .sameOrigin()
                .cacheControl();
    }
}
