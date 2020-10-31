package com.example.legalassistant.ui.consultation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.legalassistant.R
import com.example.legalassistant.models.human.MessageHuman
import kotlinx.android.synthetic.main.item_message.view.*

class ConsultationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var messages: MutableList<MessageHuman> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ConsultationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ConsultationViewHolder).bind(messages[position])
    }

    fun addAll(list: MutableList<MessageHuman>?) {
        messages.clear()
        if (list != null) messages.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    inner class ConsultationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(message: MessageHuman) {
            with(itemView) {
                with(message) {
                    if (message.incoming == true) {

                    }
                }
            }
        }
    }

}