package com.dti.rider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.OnCompleteListener

class SettingsActivity : AppCompatActivity() {

    lateinit var mGoogleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val button : Button = findViewById(R.id.button_sign_out)
        button.setOnClickListener(View.OnClickListener {
            fun onClick(v: View) {
                when (v.id) {
                    // ...
                    R.id.button_sign_out -> signOut()
                }// ...
            }
        })

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto = acct.photoUrl
        }

    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this, OnCompleteListener<Void> {
                Toast.makeText(this, "Signed Out Successfully", Toast.LENGTH_SHORT).show()
                finish()
            })

    }
}


