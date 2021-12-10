package com.divided.foodtrack.RepositoryTest;

import com.divided.foodtrack.models.Users;
import com.divided.foodtrack.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void getUsers() {
        List<Users> users = usersRepository.findAll();
        System.out.println(users);

        Assert.assertNotNull(users);
    }

    @Test
    public void getTestById() {
        Assert.assertNotNull(usersRepository.findById(1));
    }
}
