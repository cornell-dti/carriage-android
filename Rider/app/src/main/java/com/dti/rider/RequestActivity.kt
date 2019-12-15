package com.dti.rider

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

class RequestActivity : AppCompatActivity() {


    private lateinit var wcCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        getSupportActionBar()?.setTitle(Html.fromHtml("<font color='#FFFFFF'>Schedule</font>"))
        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#000000")))

        wcCheckBox = findViewById(R.id.wheelchair)

    }

    private fun Check(v: View) {
        if (wcCheckBox.isChecked()) {
            val wcMsg = "Wheelchair"
            Toast.makeText(
                this, wcMsg + "is selected",
                Toast.LENGTH_LONG
            ).show();
        }

    }
}
