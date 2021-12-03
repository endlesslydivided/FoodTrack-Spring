package com.divided.foodtrack.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/error")
public class ErrorController {

    @Autowired
    public ErrorController() {
    }

    @GetMapping()
    public ModelAndView index()
    {
        return new ModelAndView("404page");
    }

}
