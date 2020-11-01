package com.example.legalassistant.models.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class MessageRealmModel(
    @PrimaryKey
    var id: Int = 0,
    @Required
    var text: String? = "",
    @Required
    var date: String? = "",
    var incoming: Boolean? = true
): RealmObject()