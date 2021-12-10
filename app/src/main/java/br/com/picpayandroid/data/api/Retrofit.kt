package br.com.picpayandroid.data.api

import br.com.picpayandroid.presentation.userlist.viewmodel.UserListViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

//    private val gson: Gson by lazy { GsonBuilder().create() }
//    private val okHttp: OkHttpClient by lazy {
//        OkHttpClient.Builder()
//            .build()
//    }
//    private val retrofit: Retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(UserListViewModel.URL)
//            .client(okHttp)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//    }
//    private val service: PicPayService by lazy {
//        retrofit.create(PicPayService::class.java)
//    }
//
//    companion object {
//        private const val URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
//    }
}