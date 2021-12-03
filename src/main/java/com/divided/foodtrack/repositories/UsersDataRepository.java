package com.divided.foodtrack.repositories;

import com.divided.foodtrack.models.UsersData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UsersDataRepository  extends JpaRepository<UsersData, Integer> {

    @Query(value = "EXECUTE DUsersDataSelectAll", nativeQuery = true)
    List<UsersData> findAll();

    @Query(value = "EXECUTE DUsersDataSelectId :Id", nativeQuery = true)
    Optional<UsersData> findById(@Param("Id") Integer id);

    @Modifying
    @Query(value = "EXECUTE DUsersDataDelete :Id", nativeQuery = true)
    void deleteById(@Param("Id") Integer id);

    @Modifying
    @Query(value = "EXECUTE DUsersDataUpdate :Id,:Full_Name,:Birthday", nativeQuery = true)
    void update(@Param("Id") Integer id,
                 @Param("Full_Name") String fullName,
                 @Param("Birthday") Date birthday
    );

    @Modifying
    @Query(value = "EXECUTE DUsersDataAdd :Id_Data,:Full_Name,:Birthday", nativeQuery = true)
    void add(@Param("Id_Data") Integer idData,
              @Param("Full_Name") String fullName,
              @Param("Birthday") Date birthday
    );

    @Query(value = "EXECUTE DUsersDataSelectS  :LimitMin,:LimitMax,:Search", nativeQuery = true)
    List<UsersData> findPaginated(@Param("LimitMin") Integer limitMin,
                                @Param("LimitMax") Integer limitMax,
                                @Param("Search") String search
    );

    @Query(value = "EXECUTE DUsersDataSelect :LimitMin,:LimitMax", nativeQuery = true)
    List<UsersData> findPaginated(@Param("LimitMin") Integer limitMin,
                                @Param("LimitMax") Integer limitMax

    );

    @Query(value = "EXECUTE DUsersDataSelectCount",nativeQuery = true)
    int countRows();

    @Query(value = "EXECUTE DUsersDataSelectCountS :Seacrh",nativeQuery = true)
    int countRows(@Param("Seacrh")String seacrh);
}
