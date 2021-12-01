package com.divided.foodtrack.services.impl;

import com.divided.foodtrack.models.UsersParams;
import com.divided.foodtrack.repositories.UsersParamsRepository;
import com.divided.foodtrack.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersParamsService implements GeneralService<UsersParams> {

    @Autowired
    private UsersParamsRepository usersParamsRepository;

    @Override
    public void delete(int id) {
        usersParamsRepository.deleteById(id);
    }

    @Override
    public Optional<UsersParams> getById(int id) {
        return usersParamsRepository.findById(id);
    }

    @Override
    public void editItem(UsersParams item) {
        usersParamsRepository.update(item.getId(),item.getParamsDate(),item.getUserWeight(),item.getUserHeight());
    }

    @Override
    public void add(UsersParams item) {
        usersParamsRepository.add(item.getIdParams(),item.getParamsDate(),item.getUserWeight(),item.getUserHeight());

    }

    @Override
    public int getCountRows() {
        return usersParamsRepository.countRows();
    }

    @Override
    public int getCountRows(String search) {
        return usersParamsRepository.countRows(search);
    }

    @Override
    public List<UsersParams> getAll() {
        return usersParamsRepository.findAll();
    }

    @Override
    public List<UsersParams> getPaginated(int min, int max) {
        return usersParamsRepository.findPaginated(min,max);
    }

    @Override
    public List<UsersParams> getPaginated(int min, int max, String seacrh) {
        return usersParamsRepository.findPaginated(min,max,seacrh);
    }
}
