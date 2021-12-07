package br.com.picpayandroid.presentation.userlist.viewmodel

import androidx.lifecycle.*
import androidx.navigation.NavController
import br.com.picpayandroid.R
import br.com.picpayandroid.data.model.UserModel
import br.com.picpayandroid.data.repository.UserListRepository
import br.com.picpayandroid.data.api.PicPayService
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

class UserListViewModel(private val userListRepository: UserListRepository, private val navController: NavController) : ViewModel() {

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

    private val _dataRoom =
        userListRepository.allUsers.asLiveData() as MutableLiveData<List<UserModel>>
    val dataRoom: LiveData<List<UserModel>>
        get() = _dataRoom


    fun insert(listUserModel: List<UserModel>?) = viewModelScope.launch(Dispatchers.IO) {
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

                        if (response.isSuccessful && response.body() != null) {

                            insert(response.body())

                        }
                    }
                })
        }
    }

    fun startClickButtonNavigateConfirmationEspressoFragment() {
        navController.navigate(R.id.action_userListFragment_to_confirmationEspressoFragment)
    }

    companion object {
        private const val URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    }
}

