package com.dti.rider

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import cz.msebera.android.httpclient.HttpResponse
import cz.msebera.android.httpclient.NameValuePair
import cz.msebera.android.httpclient.client.HttpClient
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity
import cz.msebera.android.httpclient.client.methods.HttpPost
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder
import cz.msebera.android.httpclient.message.BasicNameValuePair
import cz.msebera.android.httpclient.util.EntityUtils

class LoginActivity : AppCompatActivity() {

    //Sign in Page
    private var RC_SIGN_IN = 1
    private lateinit var signInButton: SignInButton
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Initializing Views
        signInButton = findViewById(R.id.sign_in_button)

        val serverClientId = getString(R.string.server_client_id)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestEmail()
            .requestProfile()
            .setHostedDomain("cornell.edu")
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        signInButton.setOnClickListener {
            signIn()
        }
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
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
            val authCode = account!!.serverAuthCode
            val idToken = account!!.idToken

            val httpClient: HttpClient = HttpClientBuilder.create().build()
            val httpPost = HttpPost("10.0.2.2:3000")

            try {
                val nameValuePairs: MutableList<NameValuePair> = ArrayList<NameValuePair>(1)
                nameValuePairs.add(BasicNameValuePair("idToken", idToken))
                httpPost.entity = UrlEncodedFormEntity(nameValuePairs)
                val response: HttpResponse = httpClient.execute(httpPost)
                //val statusCode = response.statusLine.statusCode
                val responseBody = EntityUtils.toString(response.entity)
                Log.i(getString(R.string.sign_in_success), "Signed in as: $responseBody")
                println("$responseBody")
                startActivity(Intent(this, MainActivity::class.java))

            } catch (e: Exception) {
                Log.e(getString(R.string.token_error), "Error sending ID token to backend.", e)
            }
        } catch (e: ApiException) {
            Log.w(
                getString(R.string.gsi_error),
                "signInResult:failed message: " + GoogleSignInStatusCodes.getStatusCodeString(e.statusCode)
            )
            Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show()
        }
    }
}
