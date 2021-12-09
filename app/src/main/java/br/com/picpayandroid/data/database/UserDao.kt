package br.com.picpayandroid.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.picpayandroid.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM usermodel")
    fun loadAllUsers(): Flow<List<UserModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(listUserModel: List<UserModel>?)

}