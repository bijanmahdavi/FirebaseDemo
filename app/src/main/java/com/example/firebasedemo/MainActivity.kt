package com.example.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var user = FirebaseAuth.getInstance().currentUser
        if(user != null) {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        init()
    }

    private fun init() {
        button_to_register.setOnClickListener (this)
        button_to_login.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view) {
            button_to_register -> startActivity(Intent(this, RegisterActivity::class.java))
            button_to_login -> startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}