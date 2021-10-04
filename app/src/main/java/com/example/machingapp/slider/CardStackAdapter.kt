package com.example.machingapp.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.machingapp.R
import com.example.machingapp.model.UserModel

class CardStackAdapter(val context: Context, val item : List<UserModel>) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val usernickname = itemView.findViewById<TextView>(R.id.userName)
        val usersex = itemView.findViewById<TextView>(R.id.userSex)
        fun binding(data : UserModel){
            usernickname.text = data.nickname
            usersex.text = data.sex
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardStackAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardStackAdapter.ViewHolder, position: Int) {
        holder.binding(item[position])
    }

    override fun getItemCount(): Int = item.size


}