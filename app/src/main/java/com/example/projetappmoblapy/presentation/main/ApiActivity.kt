package com.example.projetappmoblapy.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetappmoblapy.R
import com.example.projetappmoblapy.data.local.models.ApiService
import com.example.projetappmoblapy.data.local.models.Disc
import com.example.projetappmoblapy.data.local.models.RestDiscResponse
import kotlinx.android.synthetic.main.row_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.row_layout)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/arnaudlapy/Projet_AppMob_LAPY/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(ApiService::class.java)
        api.fetchAllDisc().enqueue(object : Callback<RestDiscResponse>{
            override fun onFailure(call: Call<RestDiscResponse>, t: Throwable) {
                var toast: Toast = Toast.makeText(this@ApiActivity,"API NOT FOUND", Toast.LENGTH_LONG)
                toast.show()
            }
            override fun onResponse(
                call: Call<RestDiscResponse>,
                response: Response<RestDiscResponse>
            ) {
                recycler_view.apply {
                    layoutManager = LinearLayoutManager(this@ApiActivity)
                    adapter = DiscAdapter(response.body()!!.results, this@ApiActivity)
                }
            }
        })
    }
}
