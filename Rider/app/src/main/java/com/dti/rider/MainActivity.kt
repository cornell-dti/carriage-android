package com.dti.rider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {


    //This is just temporary, will use Jessie's new implementation once merged
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        val btn_profile = findViewById(R.id.profileButton) as Button
        btn_profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val btn_request = findViewById(R.id.requestButton) as Button
        btn_request.setOnClickListener {
            val intent = Intent(this, RequestActivity::class.java)
            startActivity(intent)
        }

        val btn_pickup = findViewById(R.id.pickupButton) as Button
        btn_pickup.setOnClickListener {
            val intent = Intent(this, PickUpActivity::class.java)
            startActivity(intent)
        }

        val btn_dropoff = findViewById(R.id.dropOffButton) as Button
        btn_dropoff.setOnClickListener {
            val intent = Intent(this, DropOffActivity::class.java)
            startActivity(intent)
        }

        val btn_settings = findViewById(R.id.settingsButton) as Button
        btn_settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

    }
}
