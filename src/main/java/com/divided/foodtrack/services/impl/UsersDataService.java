package com.divided.foodtrack.services.impl;

import com.divided.foodtrack.models.UsersData;
import com.divided.foodtrack.repositories.UsersDataRepository;
import com.divided.foodtrack.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersDataService implements GeneralService<UsersData> {

    private UsersDataRepository usersDataRepository;

    @Autowired
    public UsersDataService(UsersDataRepository usersDataRepository) {
        this.usersDataRepository = usersDataRepository;
    }

    @Override
    public void delete(int id) {
        usersDataRepository.deleteById(id);
    }

    @Override
    public Optional<UsersData> getById(int id) {
        return usersDataRepository.findById(id);
    }

    @Override
    public void editItem(UsersData item) {
        usersDataRepository.update(item.getId(),item.getFullName(),item.getBirthday());
    }

    @Override
    public void add(UsersData item) {
        usersDataRepository.update(item.getIdData(),item.getFullName(),item.getBirthday());
    }

    @Override
    public int getCountRows() {
        return usersDataRepository.countRows();
    }

    @Override
    public int getCountRows(String search) {
        return usersDataRepository.countRows(search);
    }

    @Override
    public List<UsersData> getAll() {
        return usersDataRepository.findAll();
    }

    @Override
    public List<UsersData> getPaginated(int min, int max) {
        return usersDataRepository.findPaginated(min,max);
    }

    @Override
    public List<UsersData> getPaginated(int min, int max, String seacrh) {
        return usersDataRepository.findPaginated(min,max,seacrh);
    }
}
