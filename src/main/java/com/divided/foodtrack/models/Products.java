package com.divided.foodtrack.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Products {
    private int id;
    private Integer idAdded;
    private String productName;
    private BigDecimal caloriesGram;
    private BigDecimal proteinsGram;
    private BigDecimal fatsGram;
    private BigDecimal carbohydratesGram;
    private String foodCategory;

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IdAdded", nullable = true)
    public Integer getIdAdded() {
        return idAdded;
    }

    public void setIdAdded(Integer idAdded) {
        this.idAdded = idAdded;
    }

    @Basic
    @Column(name = "ProductName", nullable = false, length = 200)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "CaloriesGram", nullable = false, precision = 2)
    public BigDecimal getCaloriesGram() {
        return caloriesGram;
    }

    public void setCaloriesGram(BigDecimal caloriesGram) {
        this.caloriesGram = caloriesGram;
    }

    @Basic
    @Column(name = "ProteinsGram", nullable = false, precision = 2)
    public BigDecimal getProteinsGram() {
        return proteinsGram;
    }

    public void setProteinsGram(BigDecimal proteinsGram) {
        this.proteinsGram = proteinsGram;
    }

    @Basic
    @Column(name = "FatsGram", nullable = false, precision = 2)
    public BigDecimal getFatsGram() {
        return fatsGram;
    }

    public void setFatsGram(BigDecimal fatsGram) {
        this.fatsGram = fatsGram;
    }

    @Basic
    @Column(name = "CarbohydratesGram", nullable = false, precision = 2)
    public BigDecimal getCarbohydratesGram() {
        return carbohydratesGram;
    }

    public void setCarbohydratesGram(BigDecimal carbohydratesGram) {
        this.carbohydratesGram = carbohydratesGram;
    }

    @Basic
    @Column(name = "FoodCategory", nullable = false, length = 50)
    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Products products = (Products) o;

        if (id != products.id) return false;
        if (idAdded != null ? !idAdded.equals(products.idAdded) : products.idAdded != null) return false;
        if (productName != null ? !productName.equals(products.productName) : products.productName != null)
            return false;
        if (caloriesGram != null ? !caloriesGram.equals(products.caloriesGram) : products.caloriesGram != null)
            return false;
        if (proteinsGram != null ? !proteinsGram.equals(products.proteinsGram) : products.proteinsGram != null)
            return false;
        if (fatsGram != null ? !fatsGram.equals(products.fatsGram) : products.fatsGram != null) return false;
        if (carbohydratesGram != null ? !carbohydratesGram.equals(products.carbohydratesGram) : products.carbohydratesGram != null)
            return false;
        if (foodCategory != null ? !foodCategory.equals(products.foodCategory) : products.foodCategory != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idAdded != null ? idAdded.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (caloriesGram != null ? caloriesGram.hashCode() : 0);
        result = 31 * result + (proteinsGram != null ? proteinsGram.hashCode() : 0);
        result = 31 * result + (fatsGram != null ? fatsGram.hashCode() : 0);
        result = 31 * result + (carbohydratesGram != null ? carbohydratesGram.hashCode() : 0);
        result = 31 * result + (foodCategory != null ? foodCategory.hashCode() : 0);
        return result;
    }
}
