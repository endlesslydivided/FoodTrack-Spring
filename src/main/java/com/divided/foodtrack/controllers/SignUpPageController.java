package com.divided.foodtrack.controllers;


import com.divided.foodtrack.DTO.RegistrationForm;
import com.divided.foodtrack.logging.Loggable;
import com.divided.foodtrack.services.impl.AuthAndRegServiceImpl;
import com.nimbusds.jose.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/signUp")
public class SignUpPageController {

    private final AuthAndRegServiceImpl usersService;

    @Autowired
    public SignUpPageController(AuthAndRegServiceImpl usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/register")
    @Loggable
    public ResponseEntity<Map<Object, Object>> register(@RequestBody RegistrationForm registrationForm) throws URISyntaxException {
        try
        {
            usersService.register(registrationForm);
        }
        catch (Exception e)
        {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("ErrorName",e.getMessage());
            return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
        }
        URI login = new URI("http://localhost:8080/signIn");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(login);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @GetMapping()
    public ModelAndView index()
    {
        ModelAndView modelAndView = new ModelAndView("signUpPage");
        return modelAndView;
    }
}
