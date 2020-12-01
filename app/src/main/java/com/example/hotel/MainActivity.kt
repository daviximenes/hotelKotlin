package com.example.hotel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hotel.data.Hotel
import com.example.hotel.fragment.HotelListFragment

class MainActivity : AppCompatActivity(),
        HotelListFragment.OnHotelClickListener
{

    override fun onCreate(savedInstanceState: Bundle?) {
        println("@@MAIN ACTIVITY")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)
        /*
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.coordinatorLayout, HotelListFragment.newInstance())
                    .commitNow()
        }
        */
    }

    override fun onHotelClick(hotel: Hotel) {
        showDetailsActivity(hotel.id)
    }
    private fun showDetailsActivity(hotelId: Long)
    {
        HotelDetailsActivity.open(this,hotelId)
    }


}