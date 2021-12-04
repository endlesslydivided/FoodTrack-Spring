package com.divided.foodtrack.repositories;

import com.divided.foodtrack.models.UsersParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UsersParamsRepository  extends JpaRepository<UsersParams, Integer> {

    @Query(value = "EXECUTE DUsersParamsSelectAll", nativeQuery = true)
    List<UsersParams> findAll();

    @Query(value = "EXECUTE DUsersParamsSelectId :Id", nativeQuery = true)
    Optional<UsersParams> findById(@Param("Id") Integer id);

    @Modifying
    @Query(value = "EXECUTE DUsersParamsDelete :Id", nativeQuery = true)
    void deleteById(@Param("Id") Integer id);

    @Modifying
    @Query(value = "EXECUTE DUsersParamsUpdate :Id,:Params_Date,:User_Weight,:User_Height", nativeQuery = true)
    void update(@Param("Id") Integer id,
                 @Param("Params_Date") Date paramsDate,
                 @Param("User_Weight") BigDecimal UserWeight,
                 @Param("User_Height") int UserHeight
    );

    @Modifying
    @Query(value = "EXECUTE DUsersParamsAdd :Id_Params,:Params_Date,:User_Weight,:User_Height", nativeQuery = true)
    void add(@Param("Id_Params") Integer idParams,
              @Param("Params_Date") Date paramsDate,
              @Param("User_Weight") BigDecimal UserWeight,
              @Param("User_Height") int UserHeight
    );

    @Query(value = "EXECUTE DUsersParamsSelectS  :LimitMin,:LimitMax,:Search", nativeQuery = true)
    List<UsersParams> findPaginated(@Param("LimitMin") Integer limitMin,
                                  @Param("LimitMax") Integer limitMax,
                                  @Param("Search") String search
    );

    @Query(value = "EXECUTE DUsersParamsSelectUser  :Id", nativeQuery = true)
    List<UsersParams> findByUser(@Param("Id") Integer id
    );

    @Query(value = "EXECUTE DUsersParamsSelect :LimitMin,:LimitMax", nativeQuery = true)
    List<UsersParams> findPaginated(@Param("LimitMin") Integer limitMin,
                                  @Param("LimitMax") Integer limitMax

    );

    @Query(value = "EXECUTE DUsersParamsSelectIdParams :LimitMin,:LimitMax,:IdParams", nativeQuery = true)
    List<UsersParams> findPaginatedIdParams(@Param("LimitMin") Integer limitMin,
                                    @Param("LimitMax") Integer limitMax,
                                            @Param("IdParams") Integer idParams

    );

    @Query(value = "EXECUTE DUsersParamsSelectCount",nativeQuery = true)
    int countRows();


    @Query(value = "EXECUTE DUsersParamsSelectCountS :Seacrh",nativeQuery = true)
    int countRows(@Param("Seacrh")String seacrh);

    @Query(value = "EXECUTE DUsersParamsSelectCountIdParams :IdParam",nativeQuery = true)
    int countRowsIdParams(@Param("IdParam")Integer idParams);
}
