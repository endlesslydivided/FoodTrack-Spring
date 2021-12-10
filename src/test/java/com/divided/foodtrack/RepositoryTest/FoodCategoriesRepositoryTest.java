package com.divided.foodtrack.RepositoryTest;

import com.divided.foodtrack.models.FoodCategories;
import com.divided.foodtrack.models.Users;
import com.divided.foodtrack.models.UsersParams;
import com.divided.foodtrack.repositories.FoodCategoriesRepository;
import com.divided.foodtrack.repositories.UsersParamsRepository;
import com.divided.foodtrack.repositories.UsersRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class FoodCategoriesRepositoryTest {
    @Autowired
    private FoodCategoriesRepository foodCategoriesRepository;

    @Test
    public void getFoodCategories() {
        List<FoodCategories> foodCategories = foodCategoriesRepository.findAll();
        System.out.println(foodCategories);

        Assert.assertNotNull(foodCategories);
    }

    @Test
    public void getTestById() {
        Assert.assertNotNull(foodCategoriesRepository.findById(1));
    }
}
