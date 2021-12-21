package com.divided.foodtrack.validators;

import com.divided.foodtrack.DTO.RegistrationForm;
import com.divided.foodtrack.services.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Pattern;

@Component
public class RegistrationFormValidator implements Validator {

    private final UsersService userService;

    @Autowired
    public RegistrationFormValidator(UsersService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        RegistrationForm regForm = (RegistrationForm) target;

        Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
        Pattern patternEmail = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+" +
                "(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$");

        //region LogIn Validations
        if(regForm.getUserName().length() < 1 ||regForm.getUserName().length() > 100)
            errors.rejectValue("userName", "","Поле 'Имя' должно содержать от 1 до 100 символов");
        if(pattern.matcher(regForm.getUserName()).find())
            errors.rejectValue("userName", "","Поле 'Имя' содержит запрещённый символ");

        if(regForm.getUserSurname().length() < 1 ||regForm.getUserSurname().length() > 100)
            errors.rejectValue("userSurname", "","Поле 'Фамилия' должно содержать от 1 до 100 символов");
        if(pattern.matcher(regForm.getUserSurname()).find())
            errors.rejectValue("userSurname", "","Поле 'Имя' содержит запрещённый символ");

        if(regForm.getUserLastname().length() < 1 ||regForm.getUserLastname().length() > 100)
            errors.rejectValue("userLastname", "","Поле 'Отчество' должно содержать от 1 до 100 символов");
        if(pattern.matcher(regForm.getUserLastname()).find())
            errors.rejectValue("userLastname", "","Поле 'Отчество' содержит запрещённый символ");

        if(userService.getByName(regForm.getUserLogin()).isPresent())
            errors.rejectValue("userLogin", "","Пользователя с данным логином и паролем уже существует");
        if(regForm.getUserLogin().length() < 8 ||regForm.getUserLogin().length() > 25)
            errors.rejectValue("userLogin", "","Поле 'Логин' должно содержать от 8 до 25 символов");
        if(pattern.matcher(regForm.getUserLogin()).find())
            errors.rejectValue("userLogin", "","Поле 'Логин' содержит запрещённый символ");

        if(regForm.getUserPassword().length() < 5 ||regForm.getUserPassword().length() > 20)
            errors.rejectValue("userPassword", "","Поле 'Пароль' должно содержать от 5 до 20 символов");
        if(pattern.matcher(regForm.getUserPassword()).find())
            errors.rejectValue("userPassword", "","Поле 'Пароль' содержит запрещённый символ");

        if(patternEmail.matcher(regForm.geteMail()).find() || regForm.geteMail().length() < 10 || regForm.geteMail().length() > 254 )
            errors.rejectValue("eMail", "","Поле 'Почта' неверно");


        if(regForm.getUserBirthday() == null)
        {
            errors.rejectValue("userBirthday", "","Поле 'День рождения' пустое");
        }
        else
        {
            if(regForm.getUserBirthday().after(new Date()) || regForm.getUserBirthday().toString().compareTo(" ") == 0)
                errors.rejectValue("userBirthday", "","Поле 'День рождения' содержит неверную дату");
        }

        if(regForm.getUserWeight() == null)
        {
            errors.rejectValue("userWeight", "","Поле 'Вес' должно содержать значение от 10 до 300 кг");
        }
        else
        {
            if(regForm.getUserWeight().compareTo(BigDecimal.valueOf(10)) < 0 || regForm.getUserWeight().compareTo(BigDecimal.valueOf(300)) > 0)
                errors.rejectValue("userWeight", "","Поле 'Вес' должно содержать значение от 10 до 300 кг");
        }

        if(regForm.getUserHeight() < 10 || regForm.getUserHeight() > 300 )
            errors.rejectValue("userHeight", "","Поле 'Рост' должно содержать значение от 10 до 300 см");


        //endregion



    }
}