package com.example.machingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.machingapp.authorization.IntroActivity
import com.example.machingapp.authorization.Utils
import com.example.machingapp.authorization.Utils.Companion.getUid
import com.example.machingapp.databinding.ActivityMyPageBinding
import com.example.machingapp.model.UserModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_my_page.*
import org.w3c.dom.Text

class MyPageActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMyData()
        val logout_btn = binding.logOut
        logout_btn.setOnClickListener {
            val auth = Firebase.auth
            auth.signOut()
            startActivity(Intent(this, IntroActivity::class.java ))

        }

    }

    private fun getMyData(){

        val myImage = findViewById<ImageView>(R.id.myImage)
        val myNickname = findViewById<TextView>(R.id.myNickname)
        val myUid = findViewById<TextView>(R.id.myUid)
        val mySex = findViewById<TextView>(R.id.mySex)

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val data = dataSnapshot.getValue(UserModel::class.java)
                myNickname.text = data!!.nickname.toString()
                myUid.text = data!!.uid
                mySex.text = data!!.sex

                val storageRef = Firebase.storage.reference.child(data.uid + ".png")
                storageRef.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
                    if(task.isSuccessful){
                        Glide.with(baseContext)
                            .load(task.result)
                            .into(myImage)
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        Utils.userInfoRef.child(getUid()).addValueEventListener(postListener)
    }
}