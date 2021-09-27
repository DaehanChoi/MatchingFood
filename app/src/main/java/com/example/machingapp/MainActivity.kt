package com.example.machingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.machingapp.authorization.IntroActivity
import com.example.machingapp.slider.CardStackAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction

class MainActivity : AppCompatActivity() {
    lateinit var cardStackAdapter: CardStackAdapter
    lateinit var layoutManager : CardStackLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cardStackView = findViewById<CardStackView>(R.id.cardStackView)
        val log_out_btn = findViewById<ImageView>(R.id.log_out)
        log_out_btn.setOnClickListener {
            val auth = Firebase.auth
            auth.signOut()
            startActivity(Intent(this, IntroActivity::class.java ))
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
        val tempList = mutableListOf<String>()
        tempList.addAll(listOf("a","b","c"))
        cardStackAdapter = CardStackAdapter(baseContext, tempList)
        cardStackView.layoutManager = layoutManager
        cardStackView.adapter = cardStackAdapter
    }
}