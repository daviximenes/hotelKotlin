package com.example.hotel

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.hotel.data.Hotel
import com.example.hotel.fragment.HotelListFragment

class MainActivity : AppCompatActivity(),
        HotelListFragment.OnHotelClickListener,
        SearchView.OnQueryTextListener,
        MenuItem.OnActionExpandListener
{
    private var lastSearchTerm: String = ""
    private var searchView: SearchView? = null
    private val listFragment:HotelListFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentList) as HotelListFragment
    }

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putString(EXTRA_SEARCH_TERM, lastSearchTerm)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lastSearchTerm = savedInstanceState?.getString(EXTRA_SEARCH_TERM) ?: ""
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onHotelClick(hotel: Hotel) {
        showDetailsActivity(hotel.id)
    }
    private fun showDetailsActivity(hotelId: Long)
    {
        HotelDetailsActivity.open(this,hotelId)
    }

    private fun showDetailsFragment(hotelId: Long)
    {
        searchView?.setOnQueryTextFocusChangeListener(null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.hotel, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        searchItem?.setOnActionExpandListener(this)

        searchView = searchItem?.actionView as SearchView
        searchView?.queryHint = getString(R.string.hint_search)
        searchView?.setOnQueryTextListener(this)

        if (lastSearchTerm.isNotEmpty()) {
            Handler().post {
                val query = lastSearchTerm
                searchItem.expandActionView()
                searchView?.setQuery(query, true)
                searchView?.clearFocus()
            }
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?) = true

    override fun onQueryTextChange(newText: String?): Boolean {
        lastSearchTerm = newText ?: ""
        listFragment.search(lastSearchTerm)
        return true
    }

    override fun onMenuItemActionExpand(item: MenuItem?) = true

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        lastSearchTerm = ""
        listFragment.cleanSearch()
        return true
    }
    companion object {
        const val EXTRA_SEARCH_TERM = "lastSearch"
    }


}