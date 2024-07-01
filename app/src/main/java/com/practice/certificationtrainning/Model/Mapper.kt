package com.practice.certificationtrainning.Model

import com.practice.certificationtrainning.Model.Local.Entities.ConcertsEntity
import com.practice.certificationtrainning.Model.Local.Entities.ConcertDetailEntity
import com.practice.certificationtrainning.Model.Remote.FromInternet.Concerts
import com.practice.certificationtrainning.Model.Remote.FromInternet.ConcertDetail

fun fromInternetToEntity(concerts: List<Concerts>): List<ConcertsEntity> {
    return concerts.map {
        ConcertsEntity(
            id = it.id,
            artist = it.artist,
            date = it.date,
            place = it.place,
            city = it.city,
            image = it.image,
            tickets = it.tickets

        )
    }
}

fun fromInternetToEntity(concertDetail: ConcertDetail): ConcertDetailEntity {
    return ConcertDetailEntity(
        id = concertDetail.id,
        artist = concertDetail.artist,
        date = concertDetail.date,
        place = concertDetail.place,
        city = concertDetail.city,
        image = concertDetail.image,
        tickets = concertDetail.tickets
    )
}