package com.divided.foodtrack.repositories;

import com.divided.foodtrack.models.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ReportsRepository  extends JpaRepository<Reports, Integer> {

    @Query(value = "EXECUTE DReportsSelectAll", nativeQuery = true)
    List<Reports> findAll();

    @Query(value = "EXECUTE DReportsSelectId :Id", nativeQuery = true)
    Optional<Reports> findById(@Param("Id") Integer id);

    @Query(value = "EXECUTE DReportsSelectDatePeriod :LimitMin,:LimitMax,:Date,:Period,:Id", nativeQuery = true)
    List<Reports> findByDatePeriod(@Param("LimitMin") Integer limitMin,
                                       @Param("LimitMax") Integer limitMax,
                                        @Param("Date") String date,
                                       @Param("Period") String period,
                                       @Param("Id") Integer id);

    @Modifying
    @Query(value = "EXECUTE DReportsDelete :Id", nativeQuery = true)
    void deleteById(@Param("Id") Integer id);

    @Modifying
    @Query(value = "EXECUTE DReportsUpdate :Id,:Product_Name,:Report_Date,:Eat_Period,:Day_Gram," +
            ":Day_Calories,:Day_Proteins,:Day_Fats,:Day_Carbohydrates", nativeQuery = true)
    void update(@Param("Id") Integer id,
                @Param("Product_Name") String productName,
                 @Param("Report_Date") Date reportDate,
                 @Param("Eat_Period") String eatPeriod,
                 @Param("Day_Gram") BigDecimal dayGram,
                 @Param("Day_Calories") BigDecimal dayCalories,
                 @Param("Day_Proteins") BigDecimal dayProteins,
                 @Param("Day_Fats") BigDecimal dayFats,
                 @Param("Day_Carbohydrates") BigDecimal dayCarbohydrates

                 );

    @Modifying
    @Query(value = "EXECUTE DReportsAdd :Id_Report,:Product_Name,:Report_Date,:Eat_Period,:Day_Gram," +
            ":Day_Calories,:Day_Proteins,:Day_Fats,:Day_Carbohydrates", nativeQuery = true)
    void add(@Param("Id_Report") Integer id_report,
              @Param("Product_Name") String productName,
              @Param("Report_Date") Date reportDate,
              @Param("Eat_Period") String eatPeriod,
              @Param("Day_Gram") BigDecimal dayGram,
              @Param("Day_Calories") BigDecimal dayCalories,
              @Param("Day_Proteins") BigDecimal dayProteins,
              @Param("Day_Fats") BigDecimal dayFats,
              @Param("Day_Carbohydrates") BigDecimal dayCarbohydrates
    );

    @Query(value = "EXECUTE DReportsSelectS  :LimitMin,:LimitMax,:Search", nativeQuery = true)
    List<Reports> findPaginated(@Param("LimitMin") Integer limitMin,
                                 @Param("LimitMax") Integer limitMax,
                                 @Param("Search") String search
    );

    @Query(value = "EXECUTE DReportsSelect :LimitMin,:LimitMax", nativeQuery = true)
    List<Reports> findPaginated(@Param("LimitMin") Integer limitMin,
                                 @Param("LimitMax") Integer limitMax

    );

    @Query(value = "EXECUTE DReportsSelectCount",nativeQuery = true)
    int countRows();

    @Query(value = "EXECUTE DReportsSelectCountS :Seacrh",nativeQuery = true)
    int countRows(@Param("Seacrh")String seacrh);

    @Query(value = "EXECUTE DReportsSelectDatePeriodCount :Date,:Period,:Id",nativeQuery = true)
    int countRows( @Param("Date") String date,
                   @Param("Period") String period,
                   @Param("Id") Integer id);



}
