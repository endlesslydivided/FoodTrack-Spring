package com.divided.foodtrack.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class MainPageController {

    @Autowired
    public MainPageController()
    {

    }

    @Operation(summary = "Returns index.html", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns index.html",
                    content = {@Content(mediaType = "text/html")})
    })
    @GetMapping()
    public ModelAndView index()
    {
        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }

}