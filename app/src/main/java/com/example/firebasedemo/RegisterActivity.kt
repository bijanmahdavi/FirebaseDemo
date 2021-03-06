package com.example.firebasedemo

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
    }

    private fun init() {
        button_register.setOnClickListener{
            var email = edit_text_email.text.toString()
            var password = edit_text_password.text.toString()
            var auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, object: OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if (task.isSuccessful) {
                            startActivity(Intent(this@RegisterActivity, HomeActivity::class.java))
                            //Toast.makeText(applicationContext, "User registered!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(applicationContext, "Registration Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }
        to_login_screen_text_button.setOnClickListener {

            startActivity(Intent(this, LoginActivity::class.java))

        }
    }

}