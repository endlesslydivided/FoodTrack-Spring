package com.divided.foodtrack.controllers;


import com.divided.foodtrack.DTO.RegistrationForm;
import com.divided.foodtrack.exception.RegistrationFormException;
import com.divided.foodtrack.logging.Loggable;
import com.divided.foodtrack.services.impl.AuthAndRegServiceImpl;
import com.divided.foodtrack.validators.RegistrationFormValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/signUp")
public class SignUpPageController {

    private final AuthAndRegServiceImpl usersService;
    private final RegistrationFormValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @Autowired
    public SignUpPageController(AuthAndRegServiceImpl usersService,RegistrationFormValidator validator) {
        this.usersService = usersService;
        this.validator = validator;
    }

    @Operation(summary = "Registers a user", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registers a user",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/register")
    @Loggable
    public ResponseEntity<Map<Object, Object>> register(@RequestBody RegistrationForm registrationForm) throws Exception {
        BindException bindException = new BindException(registrationForm,"registrationForm");

        validator.validate(registrationForm,bindException);
        if (bindException.hasErrors())
        {
            throw new RegistrationFormException(bindException.getAllErrors().stream().findFirst().get().getDefaultMessage());
        }
        usersService.register(registrationForm);
        URI login = new URI("http://localhost:8080/signIn");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(login);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @Operation(summary = "Returns signUpPage.html", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns signUpPage.html",
                    content = {@Content(mediaType = "text/html")})
    })
    @GetMapping()
    public ModelAndView index()
    {
        ModelAndView modelAndView = new ModelAndView("signUpPage");
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<Object,Object>> handleException(Exception ex, HttpServletResponse response)
    {
        Map<Object, Object> responseEntityMap = new HashMap<>();
        responseEntityMap.put("errorMessage", ex.getMessage());
        response.setStatus(400);
        return new ResponseEntity<>(responseEntityMap, HttpStatus.BAD_REQUEST);
    }
}
