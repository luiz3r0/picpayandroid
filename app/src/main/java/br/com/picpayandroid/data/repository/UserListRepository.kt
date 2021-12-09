package br.com.picpayandroid.data.repository

import androidx.annotation.WorkerThread
import br.com.picpayandroid.data.database.UserDao
import br.com.picpayandroid.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

class UserListRepository(private val userDao: UserDao) {

    val allUsers: Flow<List<UserModel>> = userDao.loadAllUsers()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(listUserModel: List<UserModel>?) {
        userDao.insert(listUserModel)
    }
}