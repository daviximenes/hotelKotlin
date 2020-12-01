package com.example.hotel.presenter

import com.example.hotel.view.HotelDetailsView
import com.example.hotel.data.HotelRepository

class HotelDetailsPresenter(
        private val view: HotelDetailsView,
        private val repository: HotelRepository
    )
{
    fun loadHotelDetails(id: Long)
    {
        repository.hotelById(id) { hotel ->
            if(hotel != null)
            {
                view.showHotelDetails(hotel)
            }else{
                view.errorHotelNotFound()
            }

        }
    }
}