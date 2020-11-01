package com.example.legalassistant.ui.consultation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.legalassistant.R
import com.example.legalassistant.extentions.getDate
import com.example.legalassistant.models.human.MessageHuman
import com.example.legalassistant.ui.consultation.view_holders.AbstractViewHolder
import com.example.legalassistant.ui.consultation.view_holders.ConsultantViewHolder
import com.example.legalassistant.ui.consultation.view_holders.UserViewHolder

class ConsultationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var messages: MutableList<MessageHuman> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            return ConsultantViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
            )
        } else {
            return UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_message_user, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AbstractViewHolder).bind(messages[position])
    }

    fun addAll(list: MutableList<MessageHuman>?) {
        messages.clear()
        if (list != null) messages.addAll(list)
        notifyDataSetChanged()
    }

    fun addNewMessage(text : String, incoming : Boolean) {
        val message = MessageHuman(
            text = text,
            data = getDate(),
            incoming = incoming
        )
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].incoming!!) 0 else 1
    }
}