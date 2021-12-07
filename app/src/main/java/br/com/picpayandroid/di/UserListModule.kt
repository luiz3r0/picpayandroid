package br.com.picpayandroid.di

import androidx.navigation.NavController
import br.com.picpayandroid.data.database.UserDatabase
import br.com.picpayandroid.data.repository.UserListRepository
import br.com.picpayandroid.presentation.userlist.viewmodel.UserListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { (navController : NavController) ->
        UserListViewModel(
            get(),
            navController = navController
        )
    }
}

val repositoryModule = module {
    factory {
        UserListRepository(get()) }
}

val daoModule = module {
    factory {
        UserDatabase.getInstance(androidContext()).userDao }
}