package com.example.machingapp.slider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.machingapp.MainActivity
import com.example.machingapp.R
import com.example.machingapp.authorization.IntroActivity
import com.example.machingapp.authorization.Utils.Companion.getUid
import com.google.firebase.auth.FirebaseAuth
import java.time.Duration

class SplashActivity : AppCompatActivity() {

    private val TAG = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId : String = getUid()
        setContentView(R.layout.activity_splash)
        val DURATION : Long = 3000
        if (userId == "null"){
            Handler().postDelayed({
                val intent = Intent(this, IntroActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                finish()
            }, DURATION)

        }else{
            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                finish()
            }, DURATION)

        }


    }

}