package com.example.hotel.data

data class Hotel (
        var id: Long = 0,
        var name: String = "",
        var adress:String = "",
        var rating: Float = 0.0F

){
    override fun toString(): String = name

}