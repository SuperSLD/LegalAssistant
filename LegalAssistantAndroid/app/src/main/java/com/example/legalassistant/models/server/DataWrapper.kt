package com.example.legalassistant.models.server

data class DataWrapper<T>(
        val success: Boolean?,
        val list: T?
)