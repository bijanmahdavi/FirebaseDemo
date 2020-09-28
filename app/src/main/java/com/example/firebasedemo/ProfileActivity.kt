package com.example.firebasedemo

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()

        init()
    }

    private fun init() {

        button_delete.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Account")
            builder.setMessage("Really want to delete your account??")
            builder.setPositiveButton("Yes", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, p1: Int) {
                    Toast.makeText(applicationContext, "Account Deleted!", Toast.LENGTH_SHORT).show()
                    auth = FirebaseAuth.getInstance()
                    auth.currentUser?.delete()
                    //startActivity(Intent(applicationContext, RegisterActivity::class.java))
                    //finishAffinity()
                }
            })
            builder.setNegativeButton("No", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, p1: Int) {
                    dialog?.dismiss()
                }
            })
            var alertDialog = builder.create()
            alertDialog.show()

        }

        button_view_reset_password.setOnClickListener {
            auth.sendPasswordResetEmail(auth.currentUser?.email.toString())
            Toast.makeText(applicationContext, "Link to password reset sent to your email", Toast.LENGTH_SHORT).show()
        }

        button_view_update_password.setOnClickListener {
            button_view_update_password.visibility = View.INVISIBLE
            change_password_container.visibility = View.VISIBLE
        }

        button_submit_update_password.setOnClickListener {
            if(edit_text_update_password.text.toString() != "") {
                auth.currentUser?.updatePassword(edit_text_update_password.text.toString())
                Toast.makeText(applicationContext, "Password successfully changed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
            }
            button_submit_update_password.visibility = View.INVISIBLE
            change_password_container.visibility = View.VISIBLE
        }

        button_view_update_email.setOnClickListener {
            change_email_container.visibility = View.VISIBLE
            button_view_update_email.visibility = View.INVISIBLE
        }
        button_submit_email_change.setOnClickListener {
            if(edit_text_update_email.text.toString() != "") {
                auth.currentUser?.updateEmail(edit_text_update_email.text.toString())
                startActivity(Intent(this, HomeActivity::class.java))
                Log.d("1", edit_text_update_email.text.toString())
            }
            Log.d("2", "ree")
            change_email_container.visibility = View.INVISIBLE
            button_view_update_email.visibility = View.VISIBLE
        }
    }
}