package com.example.projetappmoblapy.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetappmoblapy.domain.entity.User
import com.example.projetappmoblapy.domain.usercase.CreateUserUseCase
import com.example.projetappmoblapy.domain.usercase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
    ) : ViewModel() {

    val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()
    val createLiveData: MutableLiveData<CreateStatus> = MutableLiveData()

    fun onClickedLogin(emailUser: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val user:User? = getUserUseCase.invoke(emailUser)
            var loginStatus: LoginStatus = LoginError

            if(user != null){
                loginStatus = LoginSucces(user.email)
            }else{
                loginStatus = LoginError
            }
            withContext(Dispatchers.Main){
                loginLiveData.value = loginStatus
            }
        }
    }

    fun onClickedCreate(emailUser: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            createUserUseCase.invoke(user = User(emailUser, password))
        }
    }

    fun onClickedExist(emailUser: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val user:User? = getUserUseCase.invoke(emailUser)
            var createStatus: CreateStatus = CreateError

            if(user == null){
                createStatus = CreateSucces(emailUser, password)
            }else{
                createStatus = CreateError
            }
            withContext(Dispatchers.Main){
                createLiveData.value = createStatus
            }
        }
    }
}