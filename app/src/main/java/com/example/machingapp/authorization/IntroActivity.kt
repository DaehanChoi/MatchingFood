package com.example.machingapp.authorization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.machingapp.R
import com.example.machingapp.databinding.ActivityIntroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class IntroActivity : AppCompatActivity() {
    lateinit var binding : ActivityIntroBinding
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val logInBtn = binding.logIn

        logInBtn.setOnClickListener {
            auth = Firebase.auth
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {task ->
                    if (task.isSuccessful){

                    }else{

                    }
                }
        }
        val signinBtn = binding.signIn
        signinBtn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}