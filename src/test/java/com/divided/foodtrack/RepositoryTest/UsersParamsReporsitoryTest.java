package com.divided.foodtrack.RepositoryTest;

import com.divided.foodtrack.models.Users;
import com.divided.foodtrack.models.UsersParams;
import com.divided.foodtrack.repositories.UsersParamsRepository;
import com.divided.foodtrack.repositories.UsersRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UsersParamsReporsitoryTest {
    @Autowired
    private UsersParamsRepository usersParamsRepository;

    @Test
    public void getUserParams() {
        List<UsersParams> usersParams = usersParamsRepository.findAll();
        System.out.println(usersParams);

        Assert.assertNotNull(usersParams);
    }

    @Test
    public void getUserParamsTestById() {
        Assert.assertNotNull(usersParamsRepository.findById(1));
    }
}
