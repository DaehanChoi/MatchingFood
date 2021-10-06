package com.example.machingapp.authorization

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
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
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.io.ByteArrayOutputStream

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding
    lateinit var profileImage : ImageView
    private lateinit var auth: FirebaseAuth

    var TAG = "SignInActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Created")
        binding = ActivitySignInBinding.inflate(layoutInflater)

        profileImage = binding.profile
        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){
            uri: Uri -> profileImage.setImageURI(uri)
        }


        // 이미지 클릭시 프로필이미지를 찾게 해주는 부분
        profileImage.setOnClickListener{
            getContent.launch("image/*")
        }
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
                            imageUpload(userId)
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
    private fun imageUpload(uid : String){
        val mountainRef = Firebase.storage.reference.child( uid + ".png")
        profileImage.isDrawingCacheEnabled = true
        profileImage.buildDrawingCache()
        val bitmap = (profileImage.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainRef.putBytes(data)
        uploadTask.addOnFailureListener {

        }.addOnSuccessListener { taskSnapshot ->

        }
    }
}