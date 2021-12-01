package com.divided.foodtrack.repositories;


import com.divided.foodtrack.models.Users;
import com.divided.foodtrack.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query(value = "EXECUTE DUsersSelectAll", nativeQuery = true)
    List<Users> findAll();

    @Query(value = "EXECUTE DUsersSelectId Id", nativeQuery = true)
    Optional<Users> findById(@Param("Id") Integer id);

    @Modifying
    @Query(value = "EXECUTE DUserDel Id", nativeQuery = true)
    void deleteById(@Param("Id") Integer id);

    @Modifying
    @Query(value = "EXECUTE DUserUpdate Id,Is_Admin,User_Login,User_Password", nativeQuery = true)
    void update(@Param("Id") Integer id,
                @Param("Is_Admin") Boolean isAdmin,
                 @Param("User_Login") String userLogin,
                 @Param("User_Password") String userPassword
    );

    @Modifying
    @Query(value = "EXECUTE DUserAdd :Is_Admin,:User_Login,:User_Password", nativeQuery = true)
    void add(
              @Param("Is_Admin") Boolean isAdmin,
              @Param("User_Login") String userLogin,
              @Param("User_Password") String userPassword
    );

    @Query(value = "EXECUTE DUsersSelectLogin :User_Login", nativeQuery = true)
    Optional<Users>  getByName(
            @Param("User_Login") String userLogin
            );

    @Query(value = "EXECUTE DUsersSelectS  :LimitMin,:LimitMax,:Search", nativeQuery = true)
    List<Users> findPaginated(@Param("LimitMin") Integer limitMin,
                                  @Param("LimitMax") Integer limitMax,
                                  @Param("Search") String search
    );

    @Query(value = "EXECUTE DUsersSelect :LimitMin,:LimitMax", nativeQuery = true)
    List<Users> findPaginated(@Param("LimitMin") Integer limitMin,
                                  @Param("LimitMax") Integer limitMax

    );

    @Query(value = "EXECUTE DUsersSelectCount",nativeQuery = true)
    int countRows();

    @Query(value = "EXECUTE DUsersSelectCountS :Seacrh",nativeQuery = true)
    int countRows(@Param("Seacrh")String seacrh);

}
