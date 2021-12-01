package com.divided.foodtrack.validators;

import com.divided.foodtrack.DTO.LogInForm;
import com.divided.foodtrack.services.impl.AuthAndRegServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LogInFormValidator implements Validator {

    private final AuthAndRegServiceImpl userService;

    @Autowired
    public LogInFormValidator(AuthAndRegServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return LogInFormValidator.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        LogInForm logInForm = (LogInForm) target;


        //region LogIn Validations
        if(userService.getByName(logInForm.getUserLogin()).isEmpty())
            errors.rejectValue("userLogin", "","Пользователя с данным логином не существует");
        if(logInForm.getUserLogin().length() < 1 ||logInForm.getUserLogin().length() > 25)
            errors.rejectValue("userLogin", "","Поле 'Логин' должно содержать от 1 до 25 символов");
        if(logInForm.getUserPassword().length() < 8 ||logInForm.getUserPassword().length() > 50)
            errors.rejectValue("userPassword", "","Поле 'Пароль' должно содержать от 8 до 50 символов");
        //endregion



    }
}