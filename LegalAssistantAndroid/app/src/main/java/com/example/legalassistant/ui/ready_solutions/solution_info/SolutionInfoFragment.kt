package com.example.legalassistant.ui.ready_solutions.solution_info

import android.os.Bundle
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.legalassistant.R
import com.example.legalassistant.base.BaseFragment
import com.example.legalassistant.extentions.addSystemBottomPadding
import com.example.legalassistant.extentions.addSystemTopPadding
import com.example.legalassistant.models.human.SolutionHuman
import kotlinx.android.synthetic.main.fragment_ready_solutions.include_toolbar
import kotlinx.android.synthetic.main.fragment_solution_info.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class SolutionInfoFragment : BaseFragment(R.layout.fragment_solution_info), MvpView {
    @InjectPresenter
    lateinit var presenter: SolutionInfoPresenter
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val solution : SolutionHuman? = arguments?.getParcelable(ARG_SOLUTION)

        with(include_toolbar) {

            tvTitle.text = solution?.title
            icClose.setOnClickListener { onBackPressed() }
            addSystemTopPadding()
        }

        nested.addSystemBottomPadding()

        setView(solution)
    }

    private fun setView(solution: SolutionHuman?) {
        tvSubtitle.text = solution?.subtitle
        tvPdf.text = solution?.pdf
        tvMainInfo.text = solution?.text
    }
    override fun onBackPressed() {
        presenter.back()
    }

    companion object{
        private const val ARG_SOLUTION = "arg_solution"

        fun create(solutionHuman: SolutionHuman): SolutionInfoFragment {
            val fragment = SolutionInfoFragment()

            val args = Bundle()
            args.putParcelable(ARG_SOLUTION, solutionHuman)
            fragment.arguments = args

            return fragment
        }
    }
}