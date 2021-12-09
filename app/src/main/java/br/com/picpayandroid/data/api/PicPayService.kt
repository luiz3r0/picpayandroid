package br.com.picpayandroid.data.api

import br.com.picpayandroid.domain.model.UserModel
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<UserModel>>
}