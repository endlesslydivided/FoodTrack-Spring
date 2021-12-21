package com.divided.foodtrack.controllers;

import com.divided.foodtrack.exception.RegistrationFormException;
import com.divided.foodtrack.logging.Loggable;
import com.divided.foodtrack.DTO.LogInForm;
import com.divided.foodtrack.models.Users;
import com.divided.foodtrack.security.jwt.JwtTokenProvider;
import com.divided.foodtrack.services.impl.AuthAndRegServiceImpl;
import com.divided.foodtrack.validators.RegistrationFormValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/signIn")
public class SignInPageController {

    private final AuthAndRegServiceImpl usersService;
    AuthenticationManager authenticationManager;
    JwtTokenProvider jwtTokenProvider;


    @Autowired
    public SignInPageController(AuthAndRegServiceImpl usersService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.usersService = usersService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Operation(summary = "LogIn user", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "LogIn user",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/login")
    @Loggable
    public void login(@RequestBody  LogInForm loginForm,HttpServletResponse response) throws Exception {
        try {

            String username = loginForm.getUserLogin() ;
            Optional<Users> user = usersService.getByName(username);
            if (user.isEmpty()) {
                throw new UsernameNotFoundException("user with username: " + username + " not found");
            }
            List<SimpleGrantedAuthority> listAuth = new ArrayList<String>(Collections.singleton(user.get().isAdmin() ? "ROLE_ADMIN" : "ROLE_USER")).stream()
                    .map(role ->
                            new SimpleGrantedAuthority(role)
                    ).collect(Collectors.toList());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.get().getUserLogin(),
                    loginForm.getUserPassword()));
            String token = jwtTokenProvider.createToken(username);
            List<String> roleNames = Collections.singletonList(user.get().isAdmin() ? "ROLE_ADMIN" : "ROLE_USER");


            Cookie cookieUserName = new Cookie("username",username);
            Cookie cookieToken = new Cookie("token","Bearer%20" + token);
            Cookie cookieRoles = new Cookie("roles",roleNames.stream().findFirst().get());
            Cookie cookieId = new Cookie("id",String.valueOf(user.get().getId()));
            cookieUserName.setMaxAge(Integer.MAX_VALUE);
            cookieToken.setMaxAge(Integer.MAX_VALUE);
            cookieRoles.setMaxAge(Integer.MAX_VALUE);
            cookieId.setMaxAge(Integer.MAX_VALUE);

            cookieUserName.setPath("/");
            cookieToken.setPath("/");
            cookieRoles.setPath("/");
            cookieId.setPath("/");

            response.addCookie(cookieUserName);
            response.addCookie(cookieToken);
            response.addCookie(cookieRoles);
            response.addCookie(cookieId);

            response.sendRedirect("/");

        } catch (Exception e) {
            throw new Exception("Неверный логин или пароль");
        }
    }


    @Operation(summary = "Returns signInPage.html", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns signInPage.html",
                    content = {@Content(mediaType = "text/html")})
    })
    @Loggable
    @GetMapping()
    public ModelAndView index()
    {
        ModelAndView modelAndView = new ModelAndView("signInPage");
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<Object, Object>> handleException(Exception ex) {
        Map<Object, Object> response = new HashMap<>();
        response.put("errorMessage", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}