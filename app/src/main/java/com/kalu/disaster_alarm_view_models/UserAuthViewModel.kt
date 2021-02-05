package com.kalu.disaster_alarm_view_models

import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.kalu.api_client.EndPoints
import com.kalu.api_client.ServiceBuilder
import com.kalu.disaster_alarm.R
import com.kalu.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class UserAuthViewModel : ViewModel() {


    fun authenticateUser(email: String, password: String) : LiveData<Boolean> {
        val authenticated = MutableLiveData<Boolean>()
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getChief(email)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful ){
                    val pass =response.body()?.password
                    if (pass.toString() == password){
                        authenticated.value = true
                    }
                }
                else{
                    authenticated.value = false
                    Log.d("unsuccessful", response.code().toString() + " " )
                }

            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                authenticated.value = false
                Log.d("failure","${t.message}")
            }
        })

        return authenticated
    }

}
