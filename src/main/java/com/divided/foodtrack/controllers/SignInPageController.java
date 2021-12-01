package com.divided.foodtrack.controllers;

import com.divided.foodtrack.logging.Loggable;
import com.divided.foodtrack.DTO.LogInForm;
import com.divided.foodtrack.models.Users;
import com.divided.foodtrack.security.jwt.JwtTokenProvider;
import com.divided.foodtrack.services.impl.AuthAndRegServiceImpl;
import com.divided.foodtrack.validators.LogInFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.BindException;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/signIn")
public class SignInPageController {

    private final AuthAndRegServiceImpl usersService;
    AuthenticationManager authenticationManager;
    JwtTokenProvider jwtTokenProvider;
    private LogInFormValidator validator;

    @InitBinder(value="logInForm")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @Autowired
    public SignInPageController(AuthAndRegServiceImpl usersService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, LogInFormValidator validator) {
        this.usersService = usersService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.validator = validator;
    }


    @PostMapping("/login")
    @Loggable
    public ResponseEntity<Map<Object, Object>> login(@RequestBody LogInForm loginForm) throws Exception {
        try {
            String username = loginForm.getUserLogin() ;
            Optional<Users> user = usersService.getByName(username);
            if (user.isEmpty()) {
                throw new UsernameNotFoundException("user with username: " + username + " not found");
            }
            List<SimpleGrantedAuthority> listAuth = new ArrayList<String>(Collections.singleton(user.get().isAdmin() ? "ADMIN" : "USER")).stream()
                            .map(role ->
                                    new SimpleGrantedAuthority(role)
                            ).collect(Collectors.toList());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.get().getUserLogin(),
                    loginForm.getUserPassword()));
            String token = jwtTokenProvider.createToken(username, user.get());
            List<String> roleNames = Collections.singletonList(user.get().isAdmin() ? "ADMIN" : "USER");
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            response.put("roles", roleNames);
            response.put("id", user.get().getId());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Invalid username or password");
        }
    }

    @Loggable
    @GetMapping()
    public ModelAndView index()
    {
        ModelAndView modelAndView = new ModelAndView("signInPage");
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleBindException(BindException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
