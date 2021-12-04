package com.divided.foodtrack.DTO;

import java.math.BigDecimal;

public class ProductDTO {

    //region Fields
    private int id;
    private String username;
    private Integer idAdded;
    private String productName;
    private BigDecimal caloriesGram;
    private BigDecimal proteinsGram;
    private BigDecimal fatsGram;
    private BigDecimal carbohydratesGram;
    private String foodCategory;
//endregion

    //region Constructors
    public ProductDTO(int id, Integer idAdded, String productName, BigDecimal caloriesGram, BigDecimal proteinsGram, BigDecimal fatsGram, BigDecimal carbohydratesGram, String foodCategory) {
        this.id = id;
        this.idAdded = idAdded;
        this.productName = productName;
        this.caloriesGram = caloriesGram;
        this.proteinsGram = proteinsGram;
        this.fatsGram = fatsGram;
        this.carbohydratesGram = carbohydratesGram;
        this.foodCategory = foodCategory;
    }

    public ProductDTO(String username, String productName, BigDecimal caloriesGram, BigDecimal proteinsGram, BigDecimal fatsGram, BigDecimal carbohydratesGram, String foodCategory) {
        this.username = username;
        this.productName = productName;
        this.caloriesGram = caloriesGram;
        this.proteinsGram = proteinsGram;
        this.fatsGram = fatsGram;
        this.carbohydratesGram = carbohydratesGram;
        this.foodCategory = foodCategory;
    }

    public ProductDTO(String productName, BigDecimal caloriesGram, BigDecimal proteinsGram, BigDecimal fatsGram, BigDecimal carbohydratesGram, String foodCategory) {
        this.productName = productName;
        this.caloriesGram = caloriesGram;
        this.proteinsGram = proteinsGram;
        this.fatsGram = fatsGram;
        this.carbohydratesGram = carbohydratesGram;
        this.foodCategory = foodCategory;
    }

    public ProductDTO(Integer idAdded, String productName, BigDecimal caloriesGram, BigDecimal proteinsGram, BigDecimal fatsGram, BigDecimal carbohydratesGram, String foodCategory) {
        this.idAdded = idAdded;
        this.productName = productName;
        this.caloriesGram = caloriesGram;
        this.proteinsGram = proteinsGram;
        this.fatsGram = fatsGram;
        this.carbohydratesGram = carbohydratesGram;
        this.foodCategory = foodCategory;
    }

    public ProductDTO() {
    }
    //endregion

    //region Properties
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdAdded() {
        return idAdded;
    }

    public void setIdAdded(Integer idAdded) {
        this.idAdded = idAdded;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getCaloriesGram() {
        return caloriesGram;
    }

    public void setCaloriesGram(BigDecimal caloriesGram) {
        this.caloriesGram = caloriesGram;
    }

    public BigDecimal getProteinsGram() {
        return proteinsGram;
    }

    public void setProteinsGram(BigDecimal proteinsGram) {
        this.proteinsGram = proteinsGram;
    }

    public BigDecimal getFatsGram() {
        return fatsGram;
    }

    public void setFatsGram(BigDecimal fatsGram) {
        this.fatsGram = fatsGram;
    }

    public BigDecimal getCarbohydratesGram() {
        return carbohydratesGram;
    }

    public void setCarbohydratesGram(BigDecimal carbohydratesGram) {
        this.carbohydratesGram = carbohydratesGram;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //endregion
}
