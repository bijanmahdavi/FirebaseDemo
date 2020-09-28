package com.example.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_register.*

class HomeActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()

        init()
    }

    override fun onResume() {

        super.onResume()
        auth = FirebaseAuth.getInstance()
        logged_in_email.text = auth.currentUser?.email
    }

    override fun onRestart() {

        super.onRestart()
        auth = FirebaseAuth.getInstance()
        logged_in_email.text = auth.currentUser?.email
    }

    private fun init() {

        logged_in_email.text = auth.currentUser?.email
        button_logout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        button_user_profile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}