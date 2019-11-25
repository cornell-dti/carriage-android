package com.dti.rider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.GoogleApiClient


class SettingsActivity : AppCompatActivity() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var name : TextView
    private lateinit var email : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        getSupportActionBar()?.setTitle(Html.fromHtml("<font color='#070707'>Schedule</font>"))
        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)

        name = findViewById(R.id.sNameText)
        email = findViewById(R.id.sEmailText)

        val signOutButton: Button = findViewById(R.id.button_sign_out)
        signOutButton.setOnClickListener(View.OnClickListener {
           signOut()
        })

        val acct = GoogleSignIn.getLastSignedInAccount(this)

        if (acct != null) {
            val personName = acct.displayName
            val personEmail = acct.email

            name.setText(personName)
            email.setText(personEmail)
        }

    }

    private fun goLogInScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


    private fun signOut() {
        mGoogleSignInClient.revokeAccess()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "User logged out", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "some error", Toast.LENGTH_SHORT).show()
                }
            }
    }

//    private fun signOut() {
//        mGoogleSignInClient.signOut()
//            .addOnCompleteListener(this, OnCompleteListener<Void> {
//                Toast.makeText(this, "Signed Out Successfully", Toast.LENGTH_SHORT).show()
//                goLogInScreen()
//                finish()
//            })


}


