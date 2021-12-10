package com.divided.foodtrack.controllers;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@RestController
public class SimpleErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String errorMsg = "";
        switch (statusCode)
        {
            case 400: {
                errorMsg = "Неверный запрос. Повторите попытку.";
                break;
            }
            case 403: {
                errorMsg = "Доступ запрещён";
                break;
            }
            case 401: {
                errorMsg = "Неавторизованный доступ";
                break;
            }
            case 404: {
                errorMsg = "Ресурс не найден";
                break;
            }
            case 500: {
                errorMsg = "Ошибка сервера";
                break;
            }
        }
            ModelAndView modelAndView = new ModelAndView("errorPage");
            modelAndView.addObject("statusCode", statusCode);
            modelAndView.addObject("errorMessage", errorMsg);

            return modelAndView;
    }
}
