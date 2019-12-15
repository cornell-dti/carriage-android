package com.dti.rider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.google.android.gms.auth.api.signin.GoogleSignIn
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignInClient


class ProfileActivity : AppCompatActivity() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var name: TextView
    private lateinit var email: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        getSupportActionBar()?.setTitle(Html.fromHtml("<font color='#070707'>Schedule</font>"))
        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
        name = findViewById(R.id.nameText)
        email = findViewById(R.id.emailText)

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personEmail = acct.email

            name.setText(personName)
            email.setText(personEmail)
        }

    }


}
