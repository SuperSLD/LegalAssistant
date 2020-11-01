package com.example.legalassistant.ui.consultation.view_holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.legalassistant.models.human.MessageHuman

abstract class AbstractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(message: MessageHuman)
}