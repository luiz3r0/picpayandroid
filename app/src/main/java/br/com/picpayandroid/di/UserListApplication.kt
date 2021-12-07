package br.com.picpayandroid.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class UserListApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@UserListApplication)
            modules(viewModelModule)
            modules(repositoryModule)
            modules(daoModule)
        }
    }
}