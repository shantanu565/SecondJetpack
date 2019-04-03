package com.shantanu.example.secondjetpack;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user_details")
    LiveData<List<User>> getAllUsers();

    @Insert
    void insertUser(User user);

    @Query("DELETE FROM user_details")
    void deleteAllUsers();
}
