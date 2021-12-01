package br.com.picpayandroid.ui

import androidx.lifecycle.*
import br.com.picpayandroid.model.UserModel
import br.com.picpayandroid.retrofit.PicPayService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserListViewModel(private val userListRepository: UserListRepository) : ViewModel() {

    private val gson: Gson by lazy { GsonBuilder().create() }
    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    private val service: PicPayService by lazy {
        retrofit.create(PicPayService::class.java)
    }

//    private val _dataRetrofit = MutableLiveData<List<UserModel>>()
//    val dataRetrofit: LiveData<List<UserModel>>
//        get() = _dataRetrofit

    private val _dataRoom = userListRepository.allUsers.asLiveData() as MutableLiveData<List<UserModel>>
    val dataRoom: LiveData<List<UserModel>>
        get() = _dataRoom


    fun insert(listUserModel: List<UserModel>) = viewModelScope.launch(Dispatchers.IO) {
        userListRepository.insert(listUserModel)
    }

    init {

        startLoadUserList()
    }

    private fun startLoadUserList() {
        viewModelScope.launch(Dispatchers.IO) {
            service.getUsers()
                .enqueue(object : Callback<List<UserModel>> {
                    override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<List<UserModel>>,
                        response: Response<List<UserModel>>
                    ) {
                        if (response.isSuccessful) {

                            insert(response.body()!!)

                        }
                    }
                })
        }
    }

    companion object {
        private const val URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    }
}

class UserListViewModelFactory(private val userListRepository: UserListRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserListViewModel(userListRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

