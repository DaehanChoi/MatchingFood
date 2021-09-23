package com.example.machingapp.authorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.machingapp.R
import com.example.machingapp.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signInBtn.setOnClickListener {
            val email = binding.signInId.text.toString()
            val pwd = binding.signInPwd.text.toString()
        }
    }
}