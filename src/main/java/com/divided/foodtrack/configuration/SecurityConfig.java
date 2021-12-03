package com.divided.foodtrack.configuration;

import com.divided.foodtrack.security.MyAuthenticationEntryPoint;
import com.divided.foodtrack.security.jwt.JwtTokenFilter;
import com.divided.foodtrack.security.jwt.JwtTokenProvider;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenFilter jwtTokenFilter;

    static String USER_ENDPOINT = "/user/**";
    static String ADMIN_ENDPOINT = "/admin/**";

    public SecurityConfig(JwtTokenProvider jwtTokenProvider, JwtTokenFilter jwtTokenFilter)
    {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http = http.cors().and().csrf().disable();

        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();

        http.authorizeRequests()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/signIn/**").anonymous()
                .antMatchers("/signUp/**").anonymous()
                .antMatchers( "/").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/user/**","/admin/**").permitAll()
/*                .antMatchers("/user/**").hasAuthority("ROLE_USER")
                .antMatchers("/user/**","/admin/**").hasAuthority("ROLE_ADMIN")*/
                .anyRequest().authenticated().and()

                .exceptionHandling()
                .authenticationEntryPoint(new MyAuthenticationEntryPoint()).and();



        http.headers()
                .frameOptions()
                .sameOrigin()
                .cacheControl().and();

        http.addFilterBefore(
                jwtTokenFilter,
                UsernamePasswordAuthenticationFilter.class
        );
    }
}