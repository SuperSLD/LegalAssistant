package com.example.legalassistant.extentions

import com.example.legalassistant.models.db.MessageRealmModel
import io.realm.Realm
import io.realm.RealmResults

fun Realm.saveMessage(messageModel : MessageRealmModel) {
    executeTransaction {
        copyToRealmOrUpdate(messageModel)
    }
}

fun Realm.getMessageModels(): RealmResults<MessageRealmModel> {
    return where(MessageRealmModel::class.java).findAll()
}