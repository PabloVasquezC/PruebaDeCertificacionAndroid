package com.practice.certificationtrainning.Model.Remote


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class RetrofitClient {
    companion object {
        private const val BASE_URL = " https://jp-conciertos.onrender.com/conciertos/"

        lateinit var retrofitInstance : Retrofit

        fun getRetrofit(): ConcertsApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ConcertsApi::class.java)
        }

    }
}
