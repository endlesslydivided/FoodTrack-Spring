package com.divided.foodtrack.services.impl;

import com.divided.foodtrack.DTO.ImportProductsJSONDTO;
import com.divided.foodtrack.models.Products;
import com.divided.foodtrack.repositories.ProductsRepository;
import com.divided.foodtrack.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService implements GeneralService<Products> {

    private ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void delete(int id) {
        productsRepository.deleteById(id);
    }

    @Override
    public Optional<Products> getById(int id) {
        return productsRepository.findById(id);
    }

    @Override
    public void editItem(Products item) {
        productsRepository.update(item.getId(),item.getProductName(),item.getCaloriesGram(),item.getProteinsGram(),
                item.getFatsGram(),item.getCarbohydratesGram(),item.getFoodCategory());
    }

    @Override
    public void add(Products item) {
        productsRepository.add(item.getIdAdded(),item.getProductName(),item.getCaloriesGram(),item.getProteinsGram(),
                item.getFatsGram(),item.getCarbohydratesGram(),item.getFoodCategory());
    }

    @Override
    public int getCountRows() {
        return productsRepository.countRows();
    }

    @Override
    public int getCountRows(String search) {
        return productsRepository.countRows(search);
    }

    public int getCountRows(int id) {
        return productsRepository.countRows(id);
    }

    public int getCountRows(int id,String seacrh,String category) {
        return productsRepository.countRows(id,seacrh,category);
    }

    public int getCountRows(String seacrh,String category) {
        return productsRepository.countRows(seacrh,category);
    }

    public int getCountRows(String seacrh,int id) {
        return productsRepository.countRows(id,seacrh);
    }

    public int getCountRowsProductName(String seacrh) {
        return productsRepository.countRowsProductName(seacrh);
    }


    @Override
    public List<Products> getAll() {
        return productsRepository.findAll();
    }

    @Override
    public List<Products> getPaginated(int min, int max) {
        return productsRepository.findPaginated(min,max);
    }

    @Override
    public List<Products> getPaginated(int min, int max, String seacrh) {
        return productsRepository.findPaginatedS(min,max,seacrh);
    }

    public List<Products> getPaginatedByProductName(int min, int max, String seacrh) {
        return productsRepository.findPaginatedProductNameS(min,max,seacrh);
    }


    public List<Products> getPaginatedByUsersId(int min, int max, int id) {
        return productsRepository.findPaginatedByUserId(min,max,id);
    }
    public List<Products> getPaginatedByUsersIdCategoryS(int min, int max, int id,String search,String category) {
        return productsRepository.findPaginatedByUserIdCategoryS(min,max,id,search,category);
    }
    public List<Products> getPaginatedByCategoryS(int min, int max, String search,String category) {
        return productsRepository.findPaginatedByCategoryS(min,max,search,category);
    }

    public List<Products> getPaginatedByUserS(int min, int max, String search,int id) {
        return productsRepository.findPaginatedByUserS(min,max,search,id);
    }

    public void exportJson(String path) {
        productsRepository.exportJSON(path + "\\products.json");
    }
    public void importJson(ImportProductsJSONDTO path) {
         productsRepository.importJSON(path.getValueString());
    }

    public Optional<Products> getByName(String name) {
        return productsRepository.findByName(name);
    }
}
