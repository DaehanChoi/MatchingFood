package com.example.machingapp.authorization

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Utils {
    companion object{
        val auth = FirebaseAuth.getInstance()
        val database = Firebase.database
        val userInfoRef = database.getReference("UserInfo ")
        fun getUid() : String{
            val user : String = auth.currentUser?.uid.toString()
            return user
        }
    }
}