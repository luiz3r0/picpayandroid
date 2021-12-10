package br.com.picpayandroid.data.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    fun startRetrofit() {
        Retrofit.Builder()
            .baseUrl(URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    }

    fun startPicPayService(retrofit: Retrofit) {
        retrofit.create(PicPayService::class.java)
    }


    companion object {
        private const val URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    }
}