package com.divided.foodtrack.controllers;


import com.divided.foodtrack.DTO.ProductDTO;
import com.divided.foodtrack.logging.Loggable;
import com.divided.foodtrack.models.FoodCategories;
import com.divided.foodtrack.models.Products;
import com.divided.foodtrack.models.Reports;
import com.divided.foodtrack.models.Users;
import com.divided.foodtrack.myPage.Page;
import com.divided.foodtrack.services.impl.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserPageController {

    private ProductsService productsService;
    private ReportsService reportsService;
    private UsersParamsService usersParamsService;
    private UsersService usersService;
    private FoodCategoriesService foodCategoriesService;


    @Autowired
    public UserPageController(ProductsService productsService, ReportsService reportsService, UsersParamsService usersParamsService, UsersService usersService,FoodCategoriesService foodCategoriesService) {
        this.productsService = productsService;
        this.reportsService = reportsService;
        this.usersParamsService = usersParamsService;
        this.usersService = usersService;
        this.foodCategoriesService = foodCategoriesService;

    }

    @Loggable
    @GetMapping()
    public ModelAndView index()
    {
        return new ModelAndView("userPage");
    }

    @Loggable
    @GetMapping("/productsDiet")
    public ResponseEntity<Page<Reports>> getPaginatedDiet(@RequestParam("username") String userName,
                                                        @RequestParam("limit") int limit,
                                                         @RequestParam("page") int page,
                                                         @RequestParam("date") String date,
                                                        @RequestParam("period") String period)
    {
        Optional<Users> user = usersService.getByName(userName);
        if(user.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Page<Reports> pageFound;
        int count;
        switch (period)
        {
            case "breakfast":
                count = reportsService.getCountRows(date,"Завтрак",user.get().getId());
                pageFound = new Page<Reports>(
                    reportsService.getPaginated((page - 1) * limit, page == 1? limit + 1: page * limit - 1,date,"Завтрак",user.get().getId()),
                    page, count, count / limit + 1);break;
            case "lunch":
                count = reportsService.getCountRows(date,"Ланч",user.get().getId());
                pageFound = new Page<Reports>(
                        reportsService.getPaginated((page - 1) * limit, page == 1? limit + 1: page * limit - 1,date,"Ланч",user.get().getId()),
                        page, count, count / limit + 1);break;
            case "dinnerDay":
                count = reportsService.getCountRows(date,"Обед",user.get().getId());
                pageFound = new Page<Reports>(
                        reportsService.getPaginated((page - 1) * limit, page == 1? limit + 1: page * limit - 1,date,"Обед",user.get().getId()),
                        page, count, count / limit + 1);break;
            case "afternoon":
                count = reportsService.getCountRows(date,"Полдник",user.get().getId());
                pageFound = new Page<Reports>(
                        reportsService.getPaginated((page - 1) * limit, page == 1? limit + 1: page * limit - 1,date,"Полдник",user.get().getId()),
                        page, count, count / limit + 1);break;
            case "dinnerNight":
                count = reportsService.getCountRows(date,"Ужин",user.get().getId());
                pageFound = new Page<Reports>(
                        reportsService.getPaginated((page - 1) * limit, page == 1? limit + 1: page * limit - 1,date,"Ужин",user.get().getId()),
                        page, count, count / limit + 1);break;
            default:return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(pageFound, HttpStatus.OK);
    }

    @Loggable
    @GetMapping("/foodcategories_products")
    public ResponseEntity<List<FoodCategories>> getAllFoodCategories()
    {
        return new ResponseEntity<>(foodCategoriesService.getAll(),HttpStatus.OK);
    }

    @Loggable
    @GetMapping("/productsUser")
    public ResponseEntity<Page<Products>> getAllUsersProducts( @RequestParam("limit") int limit,
                                                                     @RequestParam("page") int page,
                                                                    @RequestParam("idAdded") int idAdded)
    {
        int count = productsService.getCountRows(idAdded);
        Page<Products> pageFound = new Page<Products>(
                productsService.getPaginatedByUsersId(
                        page==1? (page - 1) * limit: (page - 1) * limit + 1, page == 1? limit: page * limit - 1,idAdded),
                page, count, count / limit + 1);
        return new ResponseEntity<>(pageFound,HttpStatus.OK);
    }

    @Loggable
    @PostMapping("/products")
    public ResponseEntity<ProductDTO> addUsersProduct(@RequestBody ProductDTO productDTO)
    {
        List<Products> products = productsService.getAll();
        if(products.stream().filter(x ->x.getProductName() == productDTO.getProductName()).count() >= 1)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Products product = new Products();
        product.setIdAdded(productDTO.getIdAdded());
        product.setProductName(productDTO.getProductName());
        product.setCaloriesGram(productDTO.getCaloriesGram());
        product.setFatsGram(productDTO.getFatsGram());
        product.setProteinsGram(productDTO.getProteinsGram());
        product.setCarbohydratesGram(productDTO.getCarbohydratesGram());
        product.setFoodCategory(productDTO.getFoodCategory());
        productsService.add(product);

        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }
}
