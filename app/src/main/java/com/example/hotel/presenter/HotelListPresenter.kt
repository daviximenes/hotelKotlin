package com.example.hotel.presenter

import com.example.hotel.data.Hotel
import com.example.hotel.data.HotelRepository
import com.example.hotel.view.HotelListView

class HotelListPresenter (
        private val view : HotelListView,
        private val repository: HotelRepository
        )
{
    fun searchHotels(term:String)
    {
        println("@@HotelListPresenter searchHotels")

        repository.search(term) { hotels ->
            view.showHotels(hotels)
        }
    }
    fun showHotelDetails(hotel: Hotel)
    {
        println("@@HotelListPresenter showHotelDetails")

        view.showHotelDetails(hotel)
    }
}