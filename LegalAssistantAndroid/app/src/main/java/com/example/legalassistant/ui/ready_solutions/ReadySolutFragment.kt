package com.example.legalassistant.ui.ready_solutions

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.legalassistant.R
import com.example.legalassistant.base.BaseFragment
import com.example.legalassistant.extentions.addSystemBottomPadding
import com.example.legalassistant.extentions.addSystemTopPadding
import com.example.legalassistant.models.human.SolutionHuman
import com.example.legalassistant.models.server.SolutionResponse
import kotlinx.android.synthetic.main.fragment_ready_solutions.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class ReadySolutFragment : BaseFragment(R.layout.fragment_ready_solutions), ReadySolutView {

    @InjectPresenter
    lateinit var presenter: ReadySolutPresenter

    private val adapter by lazy { ReadySolutAdapter(presenter::onSolutionClick ) }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(include_toolbar) {
            tvTitle.text = resources.getString(R.string.ready_solutions)
            icClose.setOnClickListener { onBackPressed() }
            addSystemTopPadding()
        }

        with(rvSolutions) {
            adapter = this@ReadySolutFragment.adapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addSystemBottomPadding()
        }
    }

    override fun onBackPressed() {
        presenter.back()
    }

    override fun showSolutions(solutions: MutableList<SolutionHuman>) {
        adapter.addAll(solutions)
    }


}