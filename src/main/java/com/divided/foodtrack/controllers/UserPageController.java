package com.divided.foodtrack.controllers;


import com.divided.foodtrack.DTO.*;
import com.divided.foodtrack.logging.Loggable;
import com.divided.foodtrack.models.*;
import com.divided.foodtrack.myPage.Page;
import com.divided.foodtrack.services.impl.*;
import com.sun.net.httpserver.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
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

    //region PostMapping
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

    @Loggable
    @PostMapping("/productsDiet")
    public ResponseEntity<Reports> addUsersDietProduct(@RequestBody ProductUsersDietDTO productDTO)
    {
        Optional<Products> product = productsService.getByName(productDTO.getProductName());
        if(product.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Reports report = new Reports();
        report.setDayGram(productDTO.getGrams());
        report.setDayProteins(productDTO.getGrams().multiply(product.get().getProteinsGram()));
        report.setDayFats(productDTO.getGrams().multiply(product.get().getFatsGram()));
        report.setDayCarbohydrates(productDTO.getGrams().multiply(product.get().getCarbohydratesGram()));
        report.setDayCalories(productDTO.getGrams().multiply(product.get().getCaloriesGram()));
        report.setEatPeriod(productDTO.getEatPeriod());
        report.setReportDate(productDTO.getReportDate());
        report.setProductName(productDTO.getProductName());
        report.setIdReport(productDTO.getIdReport());

        reportsService.add(report);

        return new ResponseEntity<>(report,HttpStatus.OK);
    }

    @Loggable
    @PostMapping("/usersparams")
    public ResponseEntity<UsersParams> addUsersParams(@RequestBody UsersParamsDTO usersParamsDTO)
    {
        UsersParams usersParams = new UsersParams();
        usersParams.setIdParams(usersParamsDTO.getIdParams());
        usersParams.setUserWeight(usersParamsDTO.getUserWeight());
        usersParams.setParamsDate(usersParamsDTO.getParamsDate());
        usersParams.setUserHeight(usersParamsDTO.getUserHeight());
        usersParamsService.add(usersParams);

        return new ResponseEntity<>(usersParams,HttpStatus.OK);
    }
    //endregion

    //region GetMapping
    @Loggable
    @GetMapping("/usersparams")
    public ResponseEntity<Page<UsersParams>> getAllUsersParams( @RequestParam("idParams") int idParams,
                                                            @RequestParam("limit") int limit,
                                                         @RequestParam("page") int page
                                                        )
    {
        Page<UsersParams> pageFound;
        int count = usersParamsService.getCountRows(idParams);
        pageFound = new Page<>(
                usersParamsService.getPaginated(
                        (page - 1) * limit, page == 1 ? limit : page * limit - 1, idParams),
                page, count, count / limit + 1);
        return new ResponseEntity<>(pageFound,HttpStatus.OK);
    }

    @Loggable
    @GetMapping("/products")
    public ResponseEntity<Page<Products>> getAllProducts(@RequestParam("limit") int limit, @RequestParam("page") int page,
                                                         @RequestParam("productName") String productName,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("idAdded") int idAdded)
    {
        Page<Products> pageFound;
        if(idAdded != -1 && !Objects.equals(category, "null"))
        {
            int count = productsService.getCountRows(idAdded,productName,category);
            pageFound = new Page<>(
                    productsService.getPaginatedByUsersIdCategoryS(
                            (page - 1) * limit, page == 1 ? limit : page * limit - 1, idAdded, productName, category),
                    page, count, count / limit + 1);
        }
        else if(idAdded == -1 && !Objects.equals(category, "null"))
        {
            int count = productsService.getCountRows(productName,category);
            pageFound = new Page<>(
                    productsService.getPaginatedByCategoryS(
                            (page - 1) * limit, page == 1 ? limit : page * limit - 1, productName, category),
                    page, count, count / limit + 1);
        }
        else if(idAdded != -1 && category.equals("null"))
        {
            int count = productsService.getCountRows(productName,idAdded);
            pageFound = new Page<>(
                    productsService.getPaginatedByUserS(
                            (page - 1) * limit, page == 1 ? limit : page * limit - 1, productName, idAdded),
                    page, count, count / limit + 1);
        }
        else if(!Objects.equals(productName, "null"))
        {
            int count = productsService.getCountRows(productName);
            pageFound = new Page<>(
                    productsService.getPaginated(
                            (page - 1) * limit, page == 1 ? limit : page * limit - 1, productName),
                    page, count, count / limit + 1);
        }
        else
        {
            int count = productsService.getCountRows();
            pageFound = new Page<>(
                    productsService.getPaginated(
                            (page - 1) * limit, page == 1 ? limit : page * limit - 1),
                    page, count, count / limit + 1);
        }
        return new ResponseEntity<>(pageFound,HttpStatus.OK);
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
        int max = page == 1 ? limit + 1 : page * limit - 1;
        int min = (page - 1) * limit;
        switch (period)
        {
            case "breakfast":
                count = reportsService.getCountRows(date,"Завтрак",user.get().getId());
                pageFound = new Page<>(
                        reportsService.getPaginated(min, max, date, "Завтрак", user.get().getId()),
                        page, count, count / limit + 1);break;
            case "lunch":
                count = reportsService.getCountRows(date,"Ланч",user.get().getId());
                pageFound = new Page<>(
                        reportsService.getPaginated(min, max, date, "Ланч", user.get().getId()),
                        page, count, count / limit + 1);break;
            case "dinnerDay":
                count = reportsService.getCountRows(date,"Обед",user.get().getId());
                pageFound = new Page<>(
                        reportsService.getPaginated(min, max, date, "Обед", user.get().getId()),
                        page, count, count / limit + 1);break;
            case "afternoon":
                count = reportsService.getCountRows(date,"Полдник",user.get().getId());
                pageFound = new Page<>(
                        reportsService.getPaginated(min, max, date, "Полдник", user.get().getId()),
                        page, count, count / limit + 1);break;
            case "dinnerNight":
                count = reportsService.getCountRows(date,"Ужин",user.get().getId());
                pageFound = new Page<>(
                        reportsService.getPaginated(min, max, date, "Ужин", user.get().getId()),
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
        Page<Products> pageFound = new Page<>(
                productsService.getPaginatedByUsersId(
                        page == 1 ? (page - 1) * limit : (page - 1) * limit, page == 1 ? limit : page * limit - 1, idAdded),
                page, count, count / limit + 1);
        return new ResponseEntity<>(pageFound,HttpStatus.OK);
    }
    //endregion

    //region DeleteMapping
    @Loggable
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ReportsDTO> deleteUsersProduct(@PathVariable("id") int id)
    {
        productsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @DeleteMapping("/usersparams/{idUser}/{idParams}")
    public ResponseEntity<UsersParamsDTO> deleteUsersParams(@PathVariable("idUser") int id,@PathVariable("idParams") int idParams)
    {
        if(usersParamsService.getCountRows(id) == 1)
        {
            HttpHeaders headers = new HttpHeaders();
            headers.add("ErrorMessage","Last users params");
            return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
        }

        usersParamsService.delete(idParams);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @DeleteMapping("/productsDiet/{id}")
    public ResponseEntity<ReportsDTO> deleteReport(@PathVariable("id") int id)
    {
        reportsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion




}
