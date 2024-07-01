package com.practice.certificationtrainning.Model.Remote


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Response
import com.practice.certificationtrainning.Model.Remote.FromInternet.Concerts
import com.practice.certificationtrainning.Model.Remote.FromInternet.ConcertDetail
interface ConcertsApi {

    @GET("conciertos")
    suspend fun getConcerts(): Response<List<Concerts>>

    @GET("conciertos/{id}")
    suspend fun getConcertDetail(@Path("id") id: String): Response<ConcertDetail>
}