package com.divided.foodtrack.services.impl;

import com.divided.foodtrack.models.Products;
import com.divided.foodtrack.models.Reports;
import com.divided.foodtrack.repositories.ProductsRepository;
import com.divided.foodtrack.repositories.ReportsRepository;
import com.divided.foodtrack.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ReportsService implements GeneralService<Reports> {

    @Autowired
    private ReportsRepository reportsRepository;

    @Override
    public void delete(int id) {
        reportsRepository.deleteById(id);
    }

    @Override
    public Optional<Reports> getById(int id) {
        return reportsRepository.findById(id);
    }

    @Override
    public void editItem(Reports item) {
        reportsRepository.update(item.getId(),item.getProductName(),item.getReportDate(),item.getEatPeriod(),item.getDayGram(),
                item.getDayCalories(),item.getDayProteins(),item.getDayFats(),item.getDayCarbohydrates());
    }

    @Override
    public void add(Reports item) {
        reportsRepository.add(item.getIdReport(),item.getProductName(),item.getReportDate(),item.getEatPeriod(),item.getDayGram(),
                item.getDayCalories(),item.getDayProteins(),item.getDayFats(),item.getDayCarbohydrates());
    }

    @Override
    public int getCountRows() {
        return reportsRepository.countRows();
    }

    @Override
    public int getCountRows(String search) {
        return reportsRepository.countRows(search);
    }

    @Override
    public List<Reports> getAll() {
        return reportsRepository.findAll();
    }

    @Override
    public List<Reports> getPaginated(int min, int max) {
        return reportsRepository.findPaginated(min,max);
    }

    @Override
    public List<Reports> getPaginated(int min, int max, String seacrh) {
        return reportsRepository.findPaginated(min,max,seacrh);
    }

    public List<Reports> getPaginated(int min, int max, String date, String period,int id) {
        return reportsRepository.findByDatePeriod(min,max,date,period,id);
    }

    public int getCountRows(String date,String period,int id) {
        return reportsRepository.countRows(date,period,id);
    }

}
