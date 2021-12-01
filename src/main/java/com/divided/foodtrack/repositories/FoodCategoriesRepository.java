package com.divided.foodtrack.repositories;

import com.divided.foodtrack.models.FoodCategories;
import com.divided.foodtrack.models.Users;
import com.divided.foodtrack.models.UsersData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FoodCategoriesRepository extends JpaRepository<FoodCategories, Integer> {

    @Query(value = "EXECUTE DFoodCategoriesSelectAll", nativeQuery = true)
    List<FoodCategories> findAll();

    @Query(value = "EXECUTE DFoodCategoriesSelectId :Id", nativeQuery = true)
    Optional<FoodCategories> findById(@Param("Id") Integer id);

    @Modifying
    @Query(value = "EXECUTE DFoodCategoriesDelete :Id", nativeQuery = true)
    void deleteById(@Param("Id") Integer id);

    @Modifying
    @Query(value = "EXECUTE DFoodCategoriesUpdate :Id,:Category_Name", nativeQuery = true)
    void update(@Param("Id") Integer id,
                 @Param("Category_Name") String categoryName
    );

    @Modifying
    @Query(value = "EXECUTE DFoodCategoriesAdd :Category_Name", nativeQuery = true)
    void add(@Param("Category_Name") String fullName
    );

    @Query(value = "EXECUTE DFoodCategoriesSelectS :LimitMin,:LimitMax,:Search", nativeQuery = true)
    List<FoodCategories> findPaginated(@Param("LimitMin") Integer limitMin,
                       @Param("LimitMax") Integer limitMax,
                       @Param("Search") String search
    );

    @Query(value = "EXECUTE DFoodCategoriesSelect :LimitMin,:LimitMax", nativeQuery = true)
    List<FoodCategories> findPaginated(@Param("LimitMin") Integer limitMin,
                                       @Param("LimitMax") Integer limitMax

    );

    @Query(value = "EXECUTE DFoodCategoriesSelectCount",nativeQuery = true)
    int countRows();

    @Query(value = "EXECUTE DFoodCategoriesSelectCountS :Seacrh",nativeQuery = true)
    int countRows(@Param("Seacrh")String seacrh);


}
