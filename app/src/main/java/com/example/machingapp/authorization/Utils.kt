package com.example.machingapp.authorization

import com.google.firebase.auth.FirebaseAuth

class Utils {
    companion object{
        val auth = FirebaseAuth.getInstance()

        fun getUid() : String{
            val user : String = auth.currentUser?.uid.toString()
            return user
        }
    }
}