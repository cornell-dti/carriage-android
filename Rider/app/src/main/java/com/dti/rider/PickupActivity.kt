package com.dti.rider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html

class PickUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pickup)
        
        getSupportActionBar()?.hide()
    }
}
