package com.dti.rider

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spanned
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

        supportActionBar?.title = getString(R.string.schedule)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)

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
