package com.example.hotel.fragment

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import com.example.hotel.data.Hotel
import com.example.hotel.data.MemoryRepository
import com.example.hotel.presenter.HotelListPresenter
import com.example.hotel.view.HotelListView


class HotelListFragment : ListFragment(), HotelListView
{
    private val presenter = HotelListPresenter(this, MemoryRepository)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        println("@@HotelListFragment onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        presenter.searchHotels("")
    }

    override fun showHotels(hotels: List<Hotel>)
    {
        println("@@HotelListFragment showHotels")
        val adapter = ArrayAdapter<Hotel>(requireContext(), android.R.layout.simple_list_item_1, hotels)
        listAdapter = adapter
    }

    override fun showHotelDetails(hotel: Hotel)
    {
        println("@@HotelListFragment showHotelDetails")
        if(activity is OnHotelClickListener)
        {
            val listener = activity as OnHotelClickListener
            listener.onHotelClick(hotel)
        }
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long)
    {
        super.onListItemClick(l, v, position, id)
        val hotel = l.getItemAtPosition(position) as Hotel
        presenter.showHotelDetails(hotel)
    }

    interface OnHotelClickListener
    {
        fun onHotelClick(hotel: Hotel)
    }

    fun search(text:String)
    {
        presenter.searchHotels(text)
    }

    fun cleanSearch()
    {
        presenter.searchHotels("")
    }

}