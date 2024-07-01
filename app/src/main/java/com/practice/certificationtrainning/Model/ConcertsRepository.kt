package com.practice.certificationtrainning.Model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.practice.certificationtrainning.Model.Local.ConcertsDao
import com.practice.certificationtrainning.Model.Local.Entities.ConcertDetailEntity
import com.practice.certificationtrainning.Model.Local.Entities.ConcertsEntity
import com.practice.certificationtrainning.Model.Remote.RetrofitClient

class ConcertsRepository (private val concertsDao: ConcertsDao){

    //Retrofit client
    private val networkService = RetrofitClient.getRetrofit()

    //Lista de conciertos del DAO
    val concertsList = concertsDao.getAllConcerts()

    //Detalle de un curso
    val concertDetail = MutableLiveData<ConcertsEntity>()

    //Listado
    val concertsEntity = MutableLiveData<ConcertsEntity>()

    suspend fun fetchCourse(){
        val service = kotlin.runCatching { networkService.getConcerts()}

        service.onSuccess {
            when(it.code()){
                in 200 ..299 -> it.body()?.let{
                    Log.d("Cursos", it.toString())
                    ConcertsDao.insertAllConcerts(fromInternetToEntity(it))
                }
                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.d("ERROR", "${it.message}")
            }

        }
    }


    suspend fun fetchDetailCourse(id: String): ConcertDetailEntity?{
        val service = kotlin.runCatching { networkService.getConcertDetail(id)}

        return service.getOrNull()?.body()?.let{courseDetail ->
            val coursesDetailEntity = fromInternetToEntity(courseDetail)
            ConcertsDao.insertDetailCourse(coursesDetailEntity)
            coursesDetailEntity

        }
    }
}
