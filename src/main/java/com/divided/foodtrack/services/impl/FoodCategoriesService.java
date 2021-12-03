package com.divided.foodtrack.services.impl;

import com.divided.foodtrack.models.FoodCategories;
import com.divided.foodtrack.repositories.FoodCategoriesRepository;
import com.divided.foodtrack.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodCategoriesService implements GeneralService<FoodCategories> {

    private FoodCategoriesRepository foodCategoriesRepository;

    @Autowired
    public FoodCategoriesService(FoodCategoriesRepository foodCategoriesRepository) {
        this.foodCategoriesRepository = foodCategoriesRepository;
    }

    @Override
    public void delete(int id) {
        foodCategoriesRepository.deleteById(id);
    }

    @Override
    public Optional<FoodCategories> getById(int id) {
        return foodCategoriesRepository.findById(id);
    }

    @Override
    public void add(FoodCategories item) {
        foodCategoriesRepository.add(item.getCategoryName());
    }

    @Override
    public void editItem(FoodCategories item) {
        foodCategoriesRepository.update(item.getId(),item.getCategoryName());
    }

    @Override
    public List<FoodCategories> getAll() {
        return foodCategoriesRepository.findAll();
    }

    @Override
    public int getCountRows() {
        return foodCategoriesRepository.countRows();
    }

    @Override
    public int getCountRows(String search) {
        return foodCategoriesRepository.countRows(search);
    }


    @Override
    public List<FoodCategories> getPaginated(int min,int max) {
        return foodCategoriesRepository.findPaginated(min,max);
    }

    @Override
    public List<FoodCategories> getPaginated(int min,int max,String seacrh) {
        return foodCategoriesRepository.findPaginated(min,max,seacrh);
    }
}
