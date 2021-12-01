package com.divided.foodtrack.repositories;

import com.divided.foodtrack.models.FoodCategories;
import com.divided.foodtrack.models.Products;
import com.divided.foodtrack.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductsRepository  extends JpaRepository<Products, Integer>
{
    @Query(value = "EXECUTE DProductsSelectAll", nativeQuery = true)
    List<Products> findAll();

    @Query(value = "EXECUTE DProductsSelectId :Id", nativeQuery = true)
    Optional<Products> findById(@Param("Id") Integer id);

    @Query(value = "EXECUTE DProductsSelectName :Name", nativeQuery = true)
    Optional<Products> findByName(@Param("Name") String name);



    @Modifying
    @Query(value = "EXECUTE DProductsDelete :Id", nativeQuery = true)
    void deleteById(@Param("Id") Integer id);

    @Modifying
    @Query(value = "EXECUTE DProductsUpdate :Id,:Product_Name,:Calories_Gram,:Proteins_Gram,:Fats_Gram,:Carbohydrates_Gram," +
            ":Food_Category", nativeQuery = true)
    void update(@Param("Id") Integer id,
                 @Param("Product_Name") String productName,
                 @Param("Calories_Gram") BigDecimal caloriesGram,
                 @Param("Proteins_Gram") BigDecimal proteinsGram,
                 @Param("Fats_Gram") BigDecimal fatsGram,
                 @Param("Carbohydrates_Gram") BigDecimal carbohydratesGram,
                 @Param("Food_Category") String foodCategory

    );

    @Modifying
    @Query(value = "EXECUTE DProductsAdd :Id_Added,:Product_Name,:Calories_Gram,:Proteins_Gram,:Fats_Gram,:Carbohydrates_Gram,:Food_Category", nativeQuery = true)
    void add(@Param("Id_Added") Integer idAdded,
              @Param("Product_Name") String productName,
              @Param("Calories_Gram") BigDecimal caloriesGram,
              @Param("Proteins_Gram") BigDecimal proteinsGram,
              @Param("Fats_Gram") BigDecimal fatsGram,
              @Param("Carbohydrates_Gram") BigDecimal carbohydratesGram,
              @Param("Food_Category") String foodCategory
    );

    @Modifying
    @Query(value = "EXECUTE ExportProductsJSON :file_Path", nativeQuery = true)
    void exportJSON(@Param("file_Path") String filePath);

    @Modifying
    @Query(value = "EXECUTE ImportProductsFromJSON :JSON_Data", nativeQuery = true)
    void importJSON(@Param("JSON_Data") String JSONData);

    @Query(value = "EXECUTE DProductsSelectS  :LimitMin,:LimitMax,:Search", nativeQuery = true)
    List<Products> findPaginated(@Param("LimitMin") Integer limitMin,
                                       @Param("LimitMax") Integer limitMax,
                                       @Param("Search") String search
    );

    @Query(value = "EXECUTE DProductsSelect :LimitMin,:LimitMax", nativeQuery = true)
    List<Products> findPaginated(@Param("LimitMin") Integer limitMin,
                                       @Param("LimitMax") Integer limitMax

    );

    @Query(value = "EXECUTE DProductsSelectUser :LimitMin,:LimitMax,:Id", nativeQuery = true)
    List<Products>  findPaginatedByUserId(@Param("LimitMin") Integer limitMin,
                                     @Param("LimitMax") Integer limitMax,
                                     @Param("Id") int id);

    @Query(value = "EXECUTE DProductsSelectCount",nativeQuery = true)
    int countRows();

    @Query(value = "EXECUTE DProductsSelectCountS :Seacrh",nativeQuery = true)
    int countRows(@Param("Seacrh")String seacrh);

    @Query(value = "EXECUTE DProductsSelectUserCount :Id",nativeQuery = true)
    int countRows(@Param("Id")int id);

}
