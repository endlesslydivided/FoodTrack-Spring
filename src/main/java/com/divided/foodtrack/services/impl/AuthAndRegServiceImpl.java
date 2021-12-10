package com.divided.foodtrack.services.impl;

import com.divided.foodtrack.DTO.RegistrationForm;
import com.divided.foodtrack.models.Users;
import com.divided.foodtrack.repositories.UsersDataRepository;
import com.divided.foodtrack.repositories.UsersParamsRepository;
import com.divided.foodtrack.repositories.UsersRepository;
import com.divided.foodtrack.services.AuthAndRegService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuthAndRegServiceImpl implements AuthAndRegService {

    UsersRepository userRepository;
    UsersDataRepository usersDataRepository;
    UsersParamsRepository usersParamsRepository;

    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthAndRegServiceImpl(UsersRepository userRepository, UsersDataRepository usersDataRepository, UsersParamsRepository usersParamsRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.usersDataRepository = usersDataRepository;
        this.usersParamsRepository = usersParamsRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void register(RegistrationForm registrationForm) throws Exception {
        List<Users> users = userRepository.findAll();
        if (users.stream().filter(x -> x.getEmail().equals(registrationForm.geteMail())).count() != 0) {
            throw new Exception("User has already registered email");
        }
        if (users.stream().filter(x -> x.getUserLogin().equals(registrationForm.getUserLogin())).count() != 0) {
            throw new Exception("User has already registered username");
        }
        if(userRepository.countRows() == 0)
            userRepository.add(true,registrationForm.getUserLogin(), passwordEncoder.encode(registrationForm.getUserPassword()),
                    registrationForm.geteMail());
        else
            userRepository.add(false,registrationForm.getUserLogin(), passwordEncoder.encode(registrationForm.getUserPassword()),
                    registrationForm.geteMail());

        Optional<Users> userInserted = userRepository.getByName(registrationForm.getUserLogin());

        usersDataRepository.add(userInserted.get().getId(),registrationForm.getUserSurname() +" " + registrationForm.getUserName() +" "
                 + registrationForm.getUserLastname() ,registrationForm.getUserBirthday());

        usersParamsRepository.add(userInserted.get().getId(),new Date(new java.util.Date().getTime()),registrationForm.getUserWeight(),registrationForm.getUserHeight());
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<Users> getById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<Users> getByName(String name) {
        return userRepository.getByName(name);
    }

    @Override
    public void editUser(Users user) {
        userRepository.update(user.getId(),user.isAdmin(),user.getUserLogin(), user.getUserPassword(),user.getEmail());
    }

    @Override
    public List<Users> getAll() {
        return userRepository.findAll();
    }

}
