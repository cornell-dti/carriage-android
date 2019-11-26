package com.dti.rider

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class SettingsActivity : AppCompatActivity() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var signOutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        getSupportActionBar()?.setTitle(Html.fromHtml("<font color='#070707'>Schedule</font>"))
        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)

        name = findViewById(R.id.sNameText)
        email = findViewById(R.id.sEmailText)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val acct = GoogleSignIn.getLastSignedInAccount(this)

        if (acct != null) {
            val personName = acct.displayName
            val personEmail = acct.email

            name.setText(personName)
            email.setText(personEmail)
        }

        signOutButton = findViewById(R.id.button_sign_out)
        signOutButton.setOnClickListener {
            signOut()
        }

    }

    private fun logOutIntent() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun updateUI(account: GoogleSignInAccount) {
        if (account != null) {
            signOutButton.visibility = View.VISIBLE
        } else {
            signOutButton.visibility = View.GONE
        }
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                Toast.makeText(this, "Signed Out Successfully", Toast.LENGTH_SHORT).show()
                logOutIntent()
                finish()
            }
    }
}


