package com.example.legalassistant.server

class ApiService(private var api: Api) {

    fun getList() = api.getList()
}