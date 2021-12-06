package com.divided.foodtrack.controllers;


import com.divided.foodtrack.DTO.*;
import com.divided.foodtrack.logging.Loggable;
import com.divided.foodtrack.models.*;
import com.divided.foodtrack.myPage.Page;
import com.divided.foodtrack.services.impl.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/admin")
public class AdminPageController {

    private FoodCategoriesService foodCategoriesService;
    private ProductsService productsService;
    private ReportsService reportsService;
    private UsersParamsService usersParamsService;
    private UsersDataService usersDataService;
    private UsersService usersService;

    @Autowired
    public AdminPageController(FoodCategoriesService foodCategoriesService, ProductsService productsService, ReportsService reportsService, UsersParamsService usersParamsService, UsersDataService usersDataService, UsersService usersService) {
        this.foodCategoriesService = foodCategoriesService;
        this.productsService = productsService;
        this.reportsService = reportsService;
        this.usersParamsService = usersParamsService;
        this.usersDataService = usersDataService;
        this.usersService = usersService;
    }

    @Operation(summary = "Get user by name", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return adminPage.html",
                    content = {@Content(mediaType = "text/html")})
    })
    @Loggable
    @GetMapping()
    public ModelAndView index()
    {
        return new ModelAndView("adminPage");
    }

    //region UsersMapping
    @Loggable
    @GetMapping("/users")
    public ResponseEntity<Page<Users>> getPaginatedUsers(@RequestParam("limit") int limit,
                                                                     @RequestParam("page") int page,
                                                                     @RequestParam("search") String search)
    {
        Page<Users> pageFound;
        if(Objects.equals(search, "null"))
        {
            int count = usersService.getCountRows();
            pageFound = new Page<Users>(usersService.getPaginated((page - 1) * limit, page == 1? limit: page * limit - 1),
                    page, count, count / limit + 1);
        }
        else
        {
            int count = usersService.getCountRows(search);
            pageFound = new Page<Users>(usersService.getPaginated(page==1? (page - 1) * limit: (page - 1) * limit + 1, page == 1? limit: page * limit - 1,search),
                    page, count, count / limit + 1);
        }
        return new ResponseEntity<>(pageFound,HttpStatus.OK);
    }



    @Loggable
    @GetMapping("/users/{id}")
    public ResponseEntity<UsersDTO> getUser(@PathVariable("id") int id)
    {
        Optional<Users> user = usersService.getById(id);
        if(user.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UsersDTO userDTO = new UsersDTO(
                user.get().getId(),
                user.get().isAdmin(),
                user.get().getUserLogin(),
                user.get().getUserPassword());
        return new ResponseEntity<UsersDTO>(userDTO,HttpStatus.OK);
    }

    @Loggable
    @PutMapping("/users/{id}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable("id") int id,
                                                          @RequestBody UsersDTO usersDTO)
    {
        Optional<Users> user = usersService.getById(id);
        if(user.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(usersDTO.getIdEditor() == usersDTO.getId())
        {
            HttpHeaders headers = new HttpHeaders();
            headers.add("ErrorMessage","Admin change admin");
            return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
        }
        user.get().setAdmin(usersDTO.isAdmin());
        user.get().setUserLogin(usersDTO.getUserLogin());


        usersService.editItem(user.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Loggable
    @DeleteMapping("/users/{id}/{idEditor}")
    public ResponseEntity<UsersDTO> deleteUser(@PathVariable("id") int id,@PathVariable("idEditor") int idEditor)
    {
        if(id == idEditor)
        {

            HttpHeaders headers = new HttpHeaders();
            headers.add("ErrorMessage","User self delete");
            return new ResponseEntity<>(headers,HttpStatus.BAD_REQUEST);
        }
        usersService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion

    //region UsersParamsMapping
    @Loggable
    @GetMapping("/usersparams")
    public ResponseEntity<Page<UsersParams>> getPaginatedUsersParams(@RequestParam("limit") int limit,
                                                                     @RequestParam("page") int page,
                                                                     @RequestParam("search") String search)
    {
        Page<UsersParams> pageFound;
        if(Objects.equals(search, "null"))
        {
            int count = usersParamsService.getCountRows();
            pageFound = new Page<UsersParams>(usersParamsService.getPaginated(page==1? (page - 1) * limit: (page - 1) * limit + 1, page == 1? limit: page * limit - 1),
                    page, count, count / limit + 1);
        }
        else
        {
            int count = usersParamsService.getCountRows(search);
            pageFound = new Page<UsersParams>(usersParamsService.getPaginated(page==1? (page - 1) * limit: (page - 1) * limit + 1, page == 1? limit: page * limit - 1,search),
                    page, count, count / limit + 1);
        }
        return new ResponseEntity<>(pageFound,HttpStatus.OK);
    }



    @Loggable
    @GetMapping("/usersparams/{id}")
    public ResponseEntity<UsersParamsDTO> getUsersParams(@PathVariable("id") int id)
    {
        Optional<UsersParams> usersParams = usersParamsService.getById(id);
        if(usersParams.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UsersParamsDTO usersParamsDTO = new UsersParamsDTO(
                usersParams.get().getId(),
                usersParams.get().getIdParams(),
                usersParams.get().getParamsDate(),
                usersParams.get().getUserWeight(),
                usersParams.get().getUserHeight());
        return new ResponseEntity<UsersParamsDTO>(usersParamsDTO,HttpStatus.OK);
    }

    @Loggable
    @PutMapping("/usersparams/{id}")
    public ResponseEntity<UsersParamsDTO> updateUsersData(@PathVariable("id") int id,
                                                        @RequestBody UsersParamsDTO usersParamsDTO)
    {
        Optional<UsersParams> usersParams = usersParamsService.getById(id);
        if(usersParams.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usersParams.get().setIdParams(usersParamsDTO.getIdParams());
        usersParams.get().setParamsDate(usersParamsDTO.getParamsDate());
        usersParams.get().setUserWeight(usersParamsDTO.getUserWeight());
        usersParams.get().setUserHeight(usersParamsDTO.getUserHeight());


        usersParamsService.editItem(usersParams.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @PostMapping("/usersparams")
    public ResponseEntity<UsersParamsDTO> addUsersParams(@RequestBody UsersParamsDTO usersParamsDTO)
    {
        UsersParams usersParams = new UsersParams();
        usersParams.setIdParams(usersParamsDTO.getIdParams());
        usersParams.setParamsDate(usersParamsDTO.getParamsDate());
        usersParams.setUserHeight(usersParamsDTO.getUserHeight());
        usersParams.setUserWeight(usersParamsDTO.getUserWeight());

        usersParamsService.add(usersParams);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @DeleteMapping("/usersparams/{id}")
    public ResponseEntity<UsersParamsDTO> deleteUsersParams(@PathVariable("id") int id)
    {
        Optional<UsersParams> usersParams = usersParamsService.getById(id);
        if(usersParams.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(usersParamsService.getByUser(usersParams.get().getIdParams()).size() == 1)
        {
            HttpHeaders headers = new HttpHeaders();
            headers.add("ErrorMessage","Last users params");
            return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
        }
        usersParamsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion

    //region UsersDataMapping
    @Loggable
    @GetMapping("/usersdata")
    public ResponseEntity<Page<UsersData>> getPaginatedUsersData(@RequestParam("limit") int limit,
                                                             @RequestParam("page") int page,
                                                             @RequestParam("search") String search)
    {
        Page<UsersData> pageFound;
        if(Objects.equals(search, "null"))
        {
            int count = usersDataService.getCountRows();
            pageFound = new Page<UsersData>(usersDataService.getPaginated(page==1? (page - 1) * limit: (page - 1) * limit + 1, page == 1? limit: page * limit - 1),
                    page, count, count / limit + 1);
        }
        else
        {
            int count = usersDataService.getCountRows(search);
            pageFound = new Page<UsersData>(usersDataService.getPaginated(page==1? (page - 1) * limit: (page - 1) * limit + 1, page == 1? limit: page * limit - 1,search),
                    page, count, count / limit + 1);
        }
        return new ResponseEntity<>(pageFound,HttpStatus.OK);
    }



    @Loggable
    @GetMapping("/usersdata/{id}")
    public ResponseEntity<UsersDataDTO> getUsersData(@PathVariable("id") int id)
    {
        Optional<UsersData> usersData = usersDataService.getById(id);
        if(usersData.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UsersDataDTO usersDataDTO = new UsersDataDTO(
                usersData.get().getId(),
                usersData.get().getIdData(),
                usersData.get().getFullName(),
                usersData.get().getBirthday());
        return new ResponseEntity<UsersDataDTO>(usersDataDTO,HttpStatus.OK);
    }

    @Loggable
    @PutMapping("/usersdata/{id}")
    public ResponseEntity<UsersDataDTO> updateUsersData(@PathVariable("id") int id,
                                                    @RequestBody UsersDataDTO usersDataDTO)
    {
        Optional<UsersData> usersData = usersDataService.getById(id);
        if(usersData.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usersData.get().setIdData(usersDataDTO.getIdData());
        usersData.get().setFullName(usersDataDTO.getFullName());
        usersData.get().setBirthday(usersDataDTO.getBirthday());


        usersDataService.editItem(usersData.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @PostMapping("/usersdata")
    public ResponseEntity<UsersDataDTO> addUsersData(@RequestBody UsersDataDTO usersDataDTO)
    {
        UsersData usersData = new UsersData();
        usersData.setIdData(usersDataDTO.getIdData());
        usersData.setFullName(usersDataDTO.getFullName());
        usersData.setBirthday(usersDataDTO.getBirthday());

        usersDataService.add(usersData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //endregion

    //region ReportsMapping
    @Loggable
    @GetMapping("/reports")
    public ResponseEntity<Page<Reports>> getPaginatedReports(@RequestParam("limit") int limit,
                                                               @RequestParam("page") int page,
                                                               @RequestParam("search") String search)
    {
        Page<Reports> pageFound;
        if(Objects.equals(search, "null"))
        {
            int count = reportsService.getCountRows();
            pageFound = new Page<Reports>(reportsService.getPaginated(page==1? (page - 1) * limit: (page - 1) * limit + 1, page == 1? limit: page * limit - 1),
                    page, count, count / limit + 1);
        }
        else
        {
            int count = reportsService.getCountRows(search);
            pageFound = new Page<Reports>(reportsService.getPaginated(page==1? (page - 1) * limit: (page - 1) * limit + 1, page == 1? limit: page * limit - 1,search),
                    page, count, count / limit + 1);
        }
        return new ResponseEntity<>(pageFound,HttpStatus.OK);
    }



    @Loggable
    @GetMapping("/reports/{id}")
    public ResponseEntity<ReportsDTO> getReport(@PathVariable("id") int id)
    {
        Optional<Reports> report = reportsService.getById(id);
        if(report.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ReportsDTO reportDTO = new ReportsDTO(report.get().getId(),
                report.get().getIdReport(),
                report.get().getProductName(),
                report.get().getReportDate(),
                report.get().getEatPeriod(),
                report.get().getDayGram(),
                report.get().getDayCalories(),
                report.get().getDayProteins(),
                report.get().getDayFats(),
                report.get().getDayCarbohydrates());
        return new ResponseEntity<ReportsDTO>(reportDTO,HttpStatus.OK);
    }

    @Loggable
    @PutMapping("/reports/{id}")
    public ResponseEntity<ReportsDTO> updateReports(@PathVariable("id") int id,
                                                    @RequestBody ReportsDTO reportsDTO)
    {
        Optional<Reports> report = reportsService.getById(id);
        if(report.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Products> product = productsService.getByName(report.get().getProductName());
        if(product.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        report.get().setIdReport(reportsDTO.getIdReport());
        report.get().setProductName(reportsDTO.getProductName());
        report.get().setReportDate(reportsDTO.getReportDate());
        report.get().setEatPeriod(reportsDTO.getEatPeriod());
        report.get().setDayGram(reportsDTO.getDayGram());
        report.get().setDayCalories(reportsDTO.getDayGram().multiply(product.get().getCaloriesGram()));
        report.get().setDayFats(reportsDTO.getDayGram().multiply(product.get().getFatsGram()));
        report.get().setDayCarbohydrates(reportsDTO.getDayGram().multiply(product.get().getCarbohydratesGram()));
        report.get().setDayProteins(report.get().getDayGram().multiply(product.get().getProteinsGram()));

        reportsService.editItem(report.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @PostMapping("/reports")
    public ResponseEntity<ReportsDTO> addReport(@RequestBody ReportsDTO reportsDTO)
    {
        Reports report = new Reports();
        report.setIdReport(reportsDTO.getIdReport());
        report.setProductName(reportsDTO.getProductName());
        report.setReportDate(reportsDTO.getReportDate());
        report.setEatPeriod(reportsDTO.getEatPeriod());
        report.setDayGram(reportsDTO.getDayGram());
        report.setDayCalories(reportsDTO.getDayCalories());
        report.setDayFats(reportsDTO.getDayFats());
        report.setDayCarbohydrates(reportsDTO.getDayCarbohydrates());
        report.setDayProteins(reportsDTO.getDayProteins());

        reportsService.add(report);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @DeleteMapping("/reports/{id}")
    public ResponseEntity<ReportsDTO> deleteReport(@PathVariable("id") int id)
    {
        reportsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion

    //region ProductsMapping
    @Loggable
    @GetMapping("/foodcategories_products")
    public ResponseEntity<List<FoodCategories>> getAllFoodCategories()
    {
        return new ResponseEntity<>(foodCategoriesService.getAll(),HttpStatus.OK);
    }

    @Loggable
    @GetMapping("/products")
    public ResponseEntity<Page<Products>> getPaginatedProducts(@RequestParam("limit") int limit,
                                                               @RequestParam("page") int page,
                                                               @RequestParam("search") String search)
    {
        Page<Products> pageFound;
        if(Objects.equals(search, "null"))
        {
            int count = productsService.getCountRows();
            pageFound = new Page<Products>(productsService.getPaginated(page==1? (page - 1) * limit: (page - 1) * limit + 1, page == 1? limit: page * limit - 1),
                    page, count, count / limit + 1);
        }
        else
        {
            int count = productsService.getCountRows(search);
            pageFound = new Page<Products>(productsService.getPaginated(page==1? (page - 1) * limit: (page - 1) * limit + 1, page == 1? limit: page * limit - 1,search),
                    page, count, count / limit + 1);
        }
        return new ResponseEntity<>(pageFound,HttpStatus.OK);
    }



    @Loggable
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") int id)
    {
        Optional<Products> products = productsService.getById(id);
        if(products.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductDTO productDTO = new ProductDTO(products.get().getId(),
                products.get().getIdAdded(),
                products.get().getProductName(),
                products.get().getCaloriesGram(),
                products.get().getProteinsGram(),
                products.get().getFatsGram(),
                products.get().getCarbohydratesGram(),
                products.get().getFoodCategory());
        return new ResponseEntity<ProductDTO>(productDTO,HttpStatus.OK);
    }

    @Loggable
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") int id,
                                                              @RequestBody ProductDTO productDTO)
    {
        List<Products> products = productsService.getAll();
        if(products.stream().filter(x -> x.getId() != id &&
                x.getProductName() == productDTO.getProductName()).count() >1)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Optional<Products> product = productsService.getById(id);
        if(product.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.get().setIdAdded(productDTO.getIdAdded());
        product.get().setProductName(productDTO.getProductName());
        product.get().setCaloriesGram(productDTO.getCaloriesGram());
        product.get().setFatsGram(productDTO.getFatsGram());
        product.get().setProteinsGram(productDTO.getProteinsGram());
        product.get().setCarbohydratesGram(productDTO.getCarbohydratesGram());
        product.get().setFoodCategory(productDTO.getFoodCategory());

        productsService.editItem(product.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @PostMapping("/products")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO)
    {
        List<Products> foodCategories = productsService.getAll();
        if(foodCategories.stream().filter(x -> Objects.equals(x.getProductName(), productDTO.getProductName())).count() >= 1)
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
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("id") int id)
    {
        productsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @GetMapping("/products/exportJSON")
    public ResponseEntity<List<Products>> exportJSON()
    {
        return new ResponseEntity<List<Products>>(productsService.getAll(),HttpStatus.OK);
    }

    @Loggable
    @PostMapping("/products/importJSON")
    public ResponseEntity<ImportProductsJSONDTO> importJSON(@RequestBody ImportProductsJSONDTO jsonDATA)
    {
        productsService.importJson(jsonDATA);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion

    //region FoodCategoryMapping
    @Loggable
    @GetMapping("/foodcategories")
    public ResponseEntity<Page<FoodCategories>> getPaginatedFoodCategories(@RequestParam("limit") int limit,
                                                                           @RequestParam("page") int page,
                                                                           @RequestParam("search") String search)
    {
        Page<FoodCategories> pageFound;
        if(Objects.equals(search, "null"))
        {
            int count = foodCategoriesService.getCountRows();
            pageFound = new Page<FoodCategories>(foodCategoriesService.getPaginated(page==1? (page - 1) * limit: (page - 1) * limit + 1, page == 1? limit: page == 1? limit: page * limit - 1),
                    page, count, count / limit + 1);
        }
        else
        {
            int count = foodCategoriesService.getCountRows(search);
            pageFound = new Page<FoodCategories>(foodCategoriesService.getPaginated(page==1? (page - 1) * limit: (page - 1) * limit + 1, page == 1? limit: page * limit - 1,search),
                    page, count, count / limit + 1);
        }
        return new ResponseEntity<>(pageFound,HttpStatus.OK);
    }



    @Loggable
    @GetMapping("/foodcategories/{id}")
    public ResponseEntity<FoodCategoryDTO> getFoodCategory(@PathVariable("id") int id)
    {
        Optional<FoodCategories> foodCategories = foodCategoriesService.getById(id);
        if(foodCategories.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        FoodCategoryDTO foodCategoryDTO = new FoodCategoryDTO(foodCategories.get().getId(),
                foodCategories.get().getCategoryName());
        return new ResponseEntity<FoodCategoryDTO>(foodCategoryDTO,HttpStatus.OK);
    }

    @Loggable
    @PutMapping("/foodcategories/{id}")
    public ResponseEntity<FoodCategoryDTO> updateFoodCategory(@PathVariable("id") int id,
                                                              @RequestBody FoodCategoryDTO foodCategoryDTO)
    {
        List<FoodCategories> foodCategories = foodCategoriesService.getAll();
        if(foodCategories.stream().filter(x -> x.getId() != id &&
                x.getCategoryName() == foodCategoryDTO.getCategoryName()).count() >1)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Optional<FoodCategories> foodCategory = foodCategoriesService.getById(id);
        if(foodCategory.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        foodCategory.get().setCategoryName(foodCategoryDTO.getCategoryName());
        foodCategoriesService.editItem(foodCategory.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @PostMapping("/foodcategories")
    public ResponseEntity<FoodCategoryDTO> addFoodCategory(@RequestBody FoodCategoryDTO foodCategoryDTO)
    {
        List<FoodCategories> foodCategories = foodCategoriesService.getAll();
        if(foodCategories.stream().filter(x -> Objects.equals(x.getCategoryName(), foodCategoryDTO.getCategoryName())).count() >= 1)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        FoodCategories foodCategory = new FoodCategories(foodCategoryDTO.getCategoryName());
        foodCategoriesService.add(foodCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @DeleteMapping("/foodcategories/{id}")
    public ResponseEntity<FoodCategoryDTO> deleteFoodCategory(@PathVariable("id") int id)
    {
        foodCategoriesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion

}
