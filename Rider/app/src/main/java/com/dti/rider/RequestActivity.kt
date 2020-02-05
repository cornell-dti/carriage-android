package com.dti.rider

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

class RequestActivity : AppCompatActivity() {


    private lateinit var wcCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        supportActionBar?.title = getString(R.string.schedule)
        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#000000")))

        wcCheckBox = findViewById(R.id.wheelchair)

    }

    private fun Check(v: View) {
        if (wcCheckBox.isChecked()) {
            val wcMsg = getString(R.string.wheelchair)
            Toast.makeText(
                this, wcMsg + getString(R.string.is_selected),
                Toast.LENGTH_LONG
            ).show();
        }
    }

}
