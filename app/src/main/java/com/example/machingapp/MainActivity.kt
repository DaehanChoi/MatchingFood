package com.example.machingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.machingapp.authorization.IntroActivity
import com.example.machingapp.authorization.Utils.Companion.userInfoRef
import com.example.machingapp.model.UserModel
import com.example.machingapp.slider.CardStackAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction

class MainActivity : AppCompatActivity() {
    lateinit var cardStackAdapter: CardStackAdapter
    lateinit var layoutManager : CardStackLayoutManager
    private val userlist = mutableListOf<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cardStackView = findViewById<CardStackView>(R.id.cardStackView)
        val mypage_btn = findViewById<ImageView>(R.id.log_out)
        mypage_btn.setOnClickListener {

            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)

        }

        layoutManager = CardStackLayoutManager(
            baseContext,
            object : CardStackListener {
                override fun onCardDragging(direction: Direction?, ratio: Float) {

                }

                override fun onCardSwiped(direction: Direction?) {

                }

                override fun onCardRewound() {

                }

                override fun onCardCanceled() {

                }

                override fun onCardAppeared(view: View?, position: Int) {

                }

                override fun onCardDisappeared(view: View?, position: Int) {

                }
            })
        cardStackAdapter = CardStackAdapter(baseContext, userlist)
        cardStackView.layoutManager = layoutManager
        cardStackView.adapter = cardStackAdapter
        getUserData()
    }

    private fun getUserData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //val post = dataSnapshot.getValue<Post>()
                for (dataModel in dataSnapshot.children){
                    val user = dataModel.getValue(UserModel::class.java)
                    userlist.add(user!!)
                }
                cardStackAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        userInfoRef.addValueEventListener(postListener)

    }
}

