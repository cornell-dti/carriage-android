package com.dti.rider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import android.util.Log
import android.widget.Toast


class LoginActivity : AppCompatActivity() {

    private var RC_SIGN_IN = 1001
    private lateinit var signInButton: SignInButton
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Initializing Views
        signInButton = findViewById(R.id.sign_in_button)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        signInButton.setOnClickListener { signIn() }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val email = account?.getEmail()
            val split = email?.split("@")
            val domain = split?.get(1) //This Will Give You The Domain After '@'
            if (domain == "cornell.edu") {
                startActivity(Intent(this, MainActivity::class.java))
            }
        } catch (e: ApiException) {
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.statusCode)
            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
        }

    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
