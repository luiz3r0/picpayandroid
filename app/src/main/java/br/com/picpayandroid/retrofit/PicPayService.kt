package br.com.picpayandroid.retrofit

import br.com.picpayandroid.model.UserModel
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<UserModel>>
}