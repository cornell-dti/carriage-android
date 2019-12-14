package com.example.rider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< Updated upstream
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var mainFragment: MainFragment
    lateinit var secondFragment: SecondFragment
    lateinit var thirdFragment: ThirdFragment
=======
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.upcoming_ride.*

const val EXTRA_MESSAGE = "com.example.rider.MESSAGE"

class MainActivity : AppCompatActivity() {

    fun TextView.onRightDrawbaleClicked(onClicked: (view: TextView) -> Unit) {
        this.setOnTouchListener { v, event ->
            var hasConsumed = false
            if (v is TextView) {
                if (event.x >= v.width - v.totalPaddingRight) {
                    if (event.action == MotionEvent.ACTION_UP) {
                        onClicked(this)
                    }
                    hasConsumed = true
                }
            }
            hasConsumed
        }
    }
>>>>>>> Stashed changes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_navigation)

        mainFragment = MainFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, mainFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottomNavigation.setOnNavigationItemSelectedListener{ item ->

<<<<<<< Updated upstream
            when (item.itemId) {
                R.id.navigationOne -> {
                    mainFragment = MainFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, mainFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.navigationTwo -> {
                    secondFragment = SecondFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, secondFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.navigationThree -> {
                    thirdFragment = ThirdFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, thirdFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }


            }

            true
        }
=======
        // Set up UpcomingRides horizontal scrolling view
        val upcomingRideView: RecyclerView = findViewById(R.id.upcomingRides) as RecyclerView
        upcomingRideView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        val upcomingRides = ArrayList<UpcomingRide>()

        upcomingRides.add(UpcomingRide("October", "18", "10:30", "Uris Hall", "Cascadilla Hall", "Adam Denisov", "+1 123 456 7890"))
        upcomingRides.add(UpcomingRide("October", "20", "14:30", "Balch Hall", "Siri Hall", "Adam Denisov", "+1 123 456 7890"))

        val itemOnClickUpcoming: (View, Int, Int) -> Unit = { view, position, type ->
            // Toast.makeText( this@MainActivity, "Upcoming Ride " + position, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TextActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "Upcoming Ride " + position)
            }
            startActivity(intent)
        }

        val adapter = UpcomingRideAdapter(upcomingRides, itemOnClickUpcoming)
        upcomingRideView.adapter = adapter

        // Set up RideHistory vertical scrolling view
        val rideHistoryView: RecyclerView = findViewById(R.id.rideHistory)
        rideHistoryView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val historicRides = ArrayList<RideHistory>()

        historicRides.add(RideHistory("September", "13", "10:30", "Yodi Hall", "Mars Hall"))
        historicRides.add(RideHistory("September", "20", "14:30", "Gellert Hall", "Alice Hall"))
        historicRides.add(RideHistory("September", "29", "9:30", "Franklin Hall", "Georgia Hall"))
        historicRides.add(RideHistory("October", "1", "12:30", "Sellers Hall", "Spock Hall"))

        val itemOnClickHistoric: (View, Int, Int) -> Unit = { view, position, type ->
            // Toast.makeText( this@MainActivity, "Historic Ride " + position, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TextActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "Ride History " + position)
            }
            startActivity(intent)
        }

        val histAdapter = RideHistoryAdapter(historicRides, itemOnClickHistoric)
        rideHistoryView.adapter = histAdapter
>>>>>>> Stashed changes
    }

}
