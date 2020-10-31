package com.example.legalassistant.extentions

import com.example.legalassistant.models.human.SolutionHuman
import com.example.legalassistant.models.server.SolutionResponse

fun MutableList<SolutionResponse>?.toSolutionsHuman(): MutableList<SolutionHuman> {

    val solutions: MutableList<SolutionHuman> = mutableListOf()
    this?.map { response ->
        solutions.add(
            SolutionHuman(
                title = response.title.orEmpty(),
                subtitle = response.subtitle.orEmpty(),
                pdf = response.pdf.orEmpty(),
                link = response.link.orEmpty(),
                text = response.text.orEmpty()
            )
        )
    }?.toMutableList()

    return solutions
}