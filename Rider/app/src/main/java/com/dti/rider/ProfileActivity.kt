package com.dti.rider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.tasks.OnCompleteListener
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    lateinit var mGoogleSignInClient : GoogleSignInClient
    lateinit var name : TextView
    lateinit var email : TextView

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
