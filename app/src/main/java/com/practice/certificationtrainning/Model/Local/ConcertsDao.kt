package com.practice.certificationtrainning.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.practice.certificationtrainning.Model.Local.Entities.ConcertsEntity
import com.practice.certificationtrainning.Model.Local.Entities.ConcertDetailEntity
import com.practice.certificationtrainning.Model.Remote.FromInternet.Concerts

@Dao
interface ConcertsDao {

    //insert all concerts

    @Query("SELECT * FROM concerts ORDER BY date ASC")
    fun getAllConcerts(): LiveData<List<ConcertsEntity>>

    @Query("SELECT * FROM concert_detail WHERE id = :id")
    fun getConcertDetail(id: Int): LiveData<ConcertDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllConcerts(concerts: List<ConcertsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConcertDetail(concertDetail: ConcertDetailEntity)

    @Delete
    suspend fun deleteConcerts(concerts: ConcertsEntity)

    @Update
    suspend fun updateConcerts(concerts: ConcertsEntity)

    companion object {
        fun insertAllConcerts(fromInternetToEntity: List<ConcertsEntity>) {

        }

        fun insertDetailCourse(coursesDetailEntity: Any) {

        }
    }


}
