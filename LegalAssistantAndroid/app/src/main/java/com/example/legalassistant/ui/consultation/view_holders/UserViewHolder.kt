package com.example.legalassistant.ui.consultation.view_holders

import android.view.TextureView
import android.view.View
import android.widget.TextView
import com.example.legalassistant.R
import com.example.legalassistant.models.human.MessageHuman

class UserViewHolder(itemView: View) : AbstractViewHolder(itemView) {
    private var text = itemView.findViewById<TextView>(R.id.tvMessage)
    private var date = itemView.findViewById<TextView>(R.id.tvTime)


    override fun bind(message: MessageHuman) {
        text.setText(message.text)
        date.setText(message.data)
    }
}