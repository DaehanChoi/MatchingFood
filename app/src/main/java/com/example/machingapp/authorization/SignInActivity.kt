package com.example.machingapp.authorization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.machingapp.MainActivity
import com.example.machingapp.R
import com.example.machingapp.authorization.Utils.Companion.getUid
import com.example.machingapp.authorization.Utils.Companion.userInfoRef
import com.example.machingapp.databinding.ActivitySignInBinding
import com.example.machingapp.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.FirebaseAuthKtxRegistrar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding

    private lateinit var auth: FirebaseAuth

    var TAG = "SignInActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Created")
        binding = ActivitySignInBinding.inflate(layoutInflater)

        setContentView(binding.root)
        auth = Firebase.auth
        binding.signInBtn.setOnClickListener {
            val email = binding.signInId
            val pwd = binding.signInPwd
            val pwdre = binding.signInPwdRe
            val nickname = binding.signInNn
            val sex : String
            val rg  = binding.sexRadio.checkedRadioButtonId
            sex = if (rg == R.id.man) {
                "man"
            }else if(rg == R.id.woman){
                "woman"
            }else{
                "others"
            }
            Log.d(TAG, "Clicked")
            if (pwd.text.toString() != pwdre.text.toString()) {
                Toast.makeText(this, "Password Error!", Toast.LENGTH_LONG).show()
                Log.d(TAG,"Password different")
            } else {
                auth.createUserWithEmailAndPassword(email.text.toString(), pwd.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val userId = getUid()
                            val userData = UserModel(userId,nickname.text.toString(), sex)
                            userInfoRef.child(userId).setValue(userData)

                            Log.d(TAG, "createUserWithEmail:success")
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)


                        }
                    }

            }

        }

    }
}