package com.practice.certificationtrainning.Model.Local.Database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practice.certificationtrainning.Model.Local.ConcertsDao
import com.practice.certificationtrainning.Model.Local.Entities.ConcertsEntity
import com.practice.certificationtrainning.Model.Local.Entities.ConcertDetailEntity
import android.content.Context


@Database(entities = [ConcertsEntity::class, ConcertDetailEntity::class], version = 1, exportSchema = false)
abstract class ConcertsDatabase : RoomDatabase() {
    abstract fun concertsDao(): ConcertsDao

    companion object {
        @Volatile
        private var INSTANCE: ConcertsDatabase? = null

        fun getDatabase(context: Context): ConcertsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ConcertsDatabase::class.java,
                    "concerts_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}