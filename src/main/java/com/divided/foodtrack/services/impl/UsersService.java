package com.divided.foodtrack.services.impl;

import com.divided.foodtrack.models.Users;
import com.divided.foodtrack.repositories.UsersRepository;
import com.divided.foodtrack.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService implements GeneralService<Users>{

    private UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void delete(int id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Optional<Users> getById(int id) {
        return usersRepository.findById(id);
    }

    @Override
    public void editItem(Users item) {
        usersRepository.update(item.getId(),item.isAdmin(),item.getUserLogin(),item.getUserPassword(),item.getEmail());
    }

    @Override
    public void add(Users item) {
        usersRepository.add(item.isAdmin(),item.getUserLogin(),item.getUserPassword(),item.getEmail());
    }

    @Override
    public int getCountRows() {
        return usersRepository.countRows();
    }

    @Override
    public int getCountRows(String search) {
        return usersRepository.countRows(search);
    }

    @Override
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public List<Users> getPaginated(int min, int max) {
        return usersRepository.findPaginated(min,max);
    }

    @Override
    public List<Users> getPaginated(int min, int max, String seacrh) {
        return usersRepository.findPaginated(min,max,seacrh);
    }

    public Optional<Users> getByName(String name) {
        return usersRepository.getByName(name);
    }


}
