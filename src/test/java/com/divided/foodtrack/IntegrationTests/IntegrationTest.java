package com.divided.foodtrack.IntegrationTests;

import com.divided.foodtrack.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private MockMvc mockMvc;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilters(springSecurityFilterChain).build();
    }

    @Test
    void UnauthenticatedAccessAdmin() throws Exception {
        setUp();
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(status().is4xxClientError());

    }

    @Test
    void UnauthenticatedAccessUser() throws Exception {
        setUp();
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void TokenGenerationAdminAccess() throws Exception {
        setUp();
        String token = jwtTokenProvider.createToken("AdminLogin");
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin").cookie(new Cookie("token", "Bearer " + token)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void TokenGenerationSignInAccess() throws Exception {
        setUp();
        String token = jwtTokenProvider.createToken("AdminLogin");
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(MockMvcRequestBuilders.get("/signIn").cookie(new Cookie("token", "Bearer " + token)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void TokenGenerationSignUpAccess() throws Exception {
        setUp();
        String token = jwtTokenProvider.createToken("AdminLogin");
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(MockMvcRequestBuilders.get("/signUp").cookie(new Cookie("token", "Bearer " + token)))
                .andExpect(status().is4xxClientError());
    }


}
