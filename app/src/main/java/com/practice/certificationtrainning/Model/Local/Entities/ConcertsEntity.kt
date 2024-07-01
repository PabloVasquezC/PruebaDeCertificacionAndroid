package com.practice.certificationtrainning.Model.Local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "concerts")
class ConcertsEntity (

    @PrimaryKey
    val id : Int,
    val artist : String,
    val date : String,
    val place : String,
    val city : String,
    val image : String,
    val tickets : String


)