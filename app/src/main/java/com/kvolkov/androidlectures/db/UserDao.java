package com.kvolkov.androidlectures.db;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomDatabase;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * from users")
    List<User> getAllUsers();

    @Insert
    void addUser(User user);

    @Delete
    void deleteUser(User user);

}
