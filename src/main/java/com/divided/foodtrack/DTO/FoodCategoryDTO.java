package com.divided.foodtrack.DTO;

public class FoodCategoryDTO {

    private int id;
    private String categoryName;

    //region Constructors
    public FoodCategoryDTO(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public FoodCategoryDTO(String categoryName) {
        this.categoryName = categoryName;
    }

    public FoodCategoryDTO() {
    }
    //endregion

    //region Properties
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    //endregion
}
