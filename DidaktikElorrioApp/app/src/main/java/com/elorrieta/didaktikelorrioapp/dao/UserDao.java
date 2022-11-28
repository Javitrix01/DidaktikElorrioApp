package com.elorrieta.didaktikelorrioapp.dao;

import androidx.room.*;

import com.elorrieta.didaktikelorrioapp.entities.User;
import java.util.List;

//ESTRUCTURA DE UN DAO
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    //metodo para que devuelva un usuario por su id
    @Query("SELECT * FROM user WHERE uid = :userId")
    User findById(int userId);

    //metodo para ejecutar un update en la base de datos
    @Query("UPDATE user SET first_name = :first, last_name = :last, email = :email WHERE uid = :userId")
    void updateById(int userId, String first, String last, String email);
}
