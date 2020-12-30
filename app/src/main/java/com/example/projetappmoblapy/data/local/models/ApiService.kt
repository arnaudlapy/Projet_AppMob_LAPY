package com.example.projetappmoblapy.data.local.models

import retrofit2.Call
import retrofit2.http.GET

interface ApiService{

    @GET("OrikamiFakeApp.json")
    fun fetchAllDisc(): Call<RestDiscResponse>
}