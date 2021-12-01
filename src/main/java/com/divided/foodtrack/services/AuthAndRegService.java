package com.divided.foodtrack.services;

import com.divided.foodtrack.DTO.RegistrationForm;
import com.divided.foodtrack.models.Users;

import java.util.List;
import java.util.Optional;

public interface AuthAndRegService {
    void register(RegistrationForm registrationForm) throws Exception;
    public void delete(int id);
    public Optional<Users> getById(int id);
    public Optional<Users> getByName(String name);
    public void editUser(Users user);
    public List<Users> getAll();
    }