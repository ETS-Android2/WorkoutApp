package com.patrickbanez.workoutapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Data Access Object
 */
@Dao
public interface UserDao {

	@Insert
	void setInsertUser(User user);

	@Update
	void setUpdateUser(User user);

	@Delete
	void setDeleteUser(User user);

	@Query("SELECT * FROM User") //Query: Command for DB
//	LiveData<List<User>> getUserAll();
	List<User> getUserAll();
}
