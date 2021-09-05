package com.umesh.hobiapplication.localdb.dao

import androidx.room.*
import com.umesh.hobiapplication.localdb.modeldata.Users

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: Users): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserAll(users: List<Users>): List<Long>

    @Query("SELECT * FROM Users WHERE mobNum LIKE :mobNum AND password LIKE :password")
    fun readLoginData(mobNum: String, password: String):Users


    @Query("select * from users where id Like :id")
    fun getUserDataDetails(id:Long):Users

    @Query("DELETE FROM Users")
    fun deleteAll()



}