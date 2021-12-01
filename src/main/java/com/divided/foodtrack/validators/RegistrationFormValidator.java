package com.divided.foodtrack.validators;

import com.divided.foodtrack.DTO.RegistrationForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class RegistrationFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationFormValidator.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        RegistrationForm registrationFormValidator = (RegistrationForm) target;


        //region Users Validations
        if(registrationFormValidator.getUserLogin().length() < 1 ||registrationFormValidator.getUserLogin().length() > 25)
            errors.rejectValue("userLogin", "","Поле 'Логин' должно содержать от 1 до 25 символов");
        if(registrationFormValidator.getUserPassword().length() < 1 ||registrationFormValidator.getUserPassword().length() > 50)
            errors.rejectValue("userPassword", "","Поле 'Пароль' должно содержать от 1 до 50 символов");
        if(registrationFormValidator.getUserBirthday().compareTo(new Date()) != 1)
            errors.rejectValue("userBirthday", "","Поле 'День рождения' заполнено неверно");
        if(registrationFormValidator.getUserHeight() < 10 ||
                registrationFormValidator.getUserHeight() > 300)
            errors.rejectValue("userHeight", "","Поле 'Рост' должно быть в диапазоне от 10 до 300");
        if(registrationFormValidator.getUserWeight().compareTo(BigDecimal.valueOf(10)) < 0 ||
                registrationFormValidator.getUserWeight().compareTo(BigDecimal.valueOf(300)) > 0)
            errors.rejectValue("userWeight", "","Поле 'Вес' должно быть в диапазоне от 10 до 300");
        if(registrationFormValidator.getUserName().length() == 0 || registrationFormValidator.getUserName().length() > 100 )
            errors.rejectValue("userName", "","Поле 'Имя' должно содержать от 1 до 100 символов");
        if(registrationFormValidator.getUserSurname().length() == 0 || registrationFormValidator.getUserSurname().length() > 100 )
            errors.rejectValue("userSurname", "","Поле 'Фамилия' должно содержать от 1 до 100 символов");
        if(registrationFormValidator.getUserLastname().length() == 0 || registrationFormValidator.getUserLastname().length() > 100 )
            errors.rejectValue("userLastname", "","Поле 'Отчество' должно содержать от 1 до 100 символов");
        //endregion



    }
}