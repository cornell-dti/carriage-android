package com.dti.rider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DropOffActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dropoff)

        getSupportActionBar()?.hide()
    }
}
