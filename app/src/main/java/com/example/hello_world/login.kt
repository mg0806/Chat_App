@file:Suppress("ClassName")

package com.example.hello_world

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()
        editEmail = findViewById(R.id.edit_email)
        editPassword = findViewById(R.id.edit_password)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignUp = findViewById(R.id.btnSignUp)




        btnSignUp.setOnClickListener{
           val intent = Intent(this, signup::class.java)
            startActivity(intent)

        }
        btnLogin.setOnClickListener{
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            loginp(email , password)
        }

    }
    private fun loginp(email: String , password: String ) {

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // login success
                    val intent = Intent(this@login,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@login, "User does not exists", Toast.LENGTH_SHORT).show()
                }
            }
    }
}