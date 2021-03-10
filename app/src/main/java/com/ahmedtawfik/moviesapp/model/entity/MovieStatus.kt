package com.ahmedtawfik.moviesapp.model.entity

enum class MovieStatus(type: Int) {
    Rumored(0),
    Planned(1),
    InProduction(2),
    PostProduction(3),
    Released(4),
    Canceled(5)
}