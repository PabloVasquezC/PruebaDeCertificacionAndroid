package com.practice.certificationtrainning.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.practice.certificationtrainning.Model.ConcertsRepository
import com.practice.certificationtrainning.Model.Local.Database.ConcertsDatabase
import com.practice.certificationtrainning.Model.Local.Entities.ConcertsEntity
import com.practice.certificationtrainning.Model.Local.Entities.ConcertDetailEntity
import kotlinx.coroutines.launch

class ConcertsViewModel(application: Application): AndroidViewModel(application) {


    //Conexion repositorio
    private val repository : ConcertsRepository

    //Entidad
    private val courseDetailLiveData = MutableLiveData<ConcertDetailEntity>()

    //Para seleccionar
    private var isSelected : String ="-1"

    init {
        val bd = ConcertsDatabase.getDatabase(application)
        val concertsDao = bd.concertsDao()
        repository = ConcertsRepository(concertsDao)

        //Llamar a fetchCourse
        viewModelScope.launch {
            repository.fetchCourse()
        }
    }

    //Listado de los elementos
    fun getConcertsList(): LiveData<List<ConcertsEntity>> = repository.concertsList


    //Detalle de los cursos
    fun getConcertDetail(): LiveData<ConcertDetailEntity> = courseDetailLiveData

    fun getConcertDetailByIdFromInternet(id:String) = viewModelScope.launch {
        val courseDetail = repository.concertDetail

    }
}


