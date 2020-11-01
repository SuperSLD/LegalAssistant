package com.example.legalassistant.extentions

import com.example.legalassistant.models.db.MessageRealmModel
import com.example.legalassistant.models.human.MessageHuman
import com.example.legalassistant.models.human.SolutionHuman
import com.example.legalassistant.models.server.SolutionResponse
import io.realm.RealmResults

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

fun MessageHuman.toMessageRealmModel(id: Int):MessageRealmModel {
    return MessageRealmModel(
        id = id,
        text = text,
        date = data,
        incoming = incoming)
}

fun RealmResults<MessageRealmModel>.toMessageHuman() : MutableList<MessageHuman> {
    val messages: MutableList<MessageHuman> = mutableListOf()
    this.map { realmModel ->
        messages.add(
            MessageHuman(
                text = realmModel.text,
                data = realmModel.date,
                incoming = realmModel.incoming
            )
        )
    }.toMutableList()

    return messages
}