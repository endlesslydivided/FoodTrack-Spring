package com.divided.foodtrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class FoodTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodTrackApplication.class, args);
    }

}
