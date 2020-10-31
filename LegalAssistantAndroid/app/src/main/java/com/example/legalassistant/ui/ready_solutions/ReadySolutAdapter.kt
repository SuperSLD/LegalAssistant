package com.example.legalassistant.ui.ready_solutions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.legalassistant.R
import com.example.legalassistant.models.human.SolutionHuman
import com.example.legalassistant.models.server.SolutionResponse
import kotlinx.android.synthetic.main.item_list.view.*

class ReadySolutAdapter(private val onSolutionClick: (SolutionHuman) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val solutions: MutableList<SolutionHuman> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SolutionViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    fun addAll(list: MutableList<SolutionHuman>?) {
        solutions.clear()
        if (list != null) solutions.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SolutionViewHolder).bind(solutions[position])
    }

    override fun getItemCount(): Int {
        return solutions.size
    }

    inner class SolutionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(solution: SolutionHuman) {
            with(itemView) {
                with(solution) {
                     tvName.text = title
                }
                setOnClickListener { onSolutionClick(solution) }
            }
        }
    }

}