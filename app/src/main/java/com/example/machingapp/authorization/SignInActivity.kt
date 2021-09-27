package com.example.machingapp.authorization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.machingapp.MainActivity
import com.example.machingapp.R
import com.example.machingapp.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.FirebaseAuthKtxRegistrar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignInBinding

    private lateinit var auth: FirebaseAuth

    var TAG = "SignInActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)

        setContentView(binding.root)

        auth = Firebase.auth

        binding.signInBtn.setOnClickListener {
            val email = binding.signInId.text.toString()
            val pwd = binding.signInPwd.text.toString()

            auth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()

                    }
                }
        }
    }
}