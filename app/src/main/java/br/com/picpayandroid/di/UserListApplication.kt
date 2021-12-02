package br.com.picpayandroid.di

import android.app.Application
import br.com.picpayandroid.db.UserDatabase
import br.com.picpayandroid.ui.userlist.UserListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class UserListApplication: Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    private val userDatabase by lazy { UserDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { UserListRepository(userDatabase.userDao()) }
}