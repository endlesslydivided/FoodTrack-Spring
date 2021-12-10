package com.divided.foodtrack.controllers;



import com.divided.foodtrack.exception.UserNameNotFoundException;
import com.divided.foodtrack.logging.Loggable;
import com.divided.foodtrack.models.Users;
import com.divided.foodtrack.models.UsersParams;
import com.divided.foodtrack.services.impl.UsersParamsService;
import com.divided.foodtrack.services.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Comparator;
import java.util.Optional;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/")
public class MailController {
    JavaMailSender emailSender;
    @Autowired
    UsersService userService;
    @Autowired
    UsersParamsService usersParamsService;


    public MailController(JavaMailSender emailSender,
                          UsersService userService,
                          UsersParamsService usersParamsService) {
        this.emailSender = emailSender;
        this.userService = userService;
        this.usersParamsService = usersParamsService;
    }

    @GetMapping(value = "sendNotify/{id}")
    @Loggable
    public ResponseEntity<Object> sendSimpleEmail(@PathVariable("id") int id) throws UserNameNotFoundException {
        SimpleMailMessage message = new SimpleMailMessage();
        Optional<Users> user = userService.getById(id);
        if(user.isEmpty())
        {
            throw new UserNameNotFoundException("Username isn't found");
        }
        Optional<UsersParams> usersParams = usersParamsService.getByUser(id).stream().max(Comparator.comparingInt(UsersParams::getId));

        message.setTo(user.get().getEmail());
        message.setSubject("FoodTrack");
        StringBuilder msg = new StringBuilder("Привет! Твой последний отчёт был создан " + usersParams.get().getParamsDate()+
                ". Твои параметры на тот момент: рост - " + usersParams.get().getUserHeight() + " см ; вес - "+ usersParams.get().getUserWeight() +" кг. Заходи в приложение и продолжай следить за своим рационом!"+"\n\n");


        message.setText(String.valueOf(msg));
        this.emailSender.send(message);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

