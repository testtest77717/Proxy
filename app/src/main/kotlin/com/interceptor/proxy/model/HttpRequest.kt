package com.interceptor.proxy.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "http_requests")
data class HttpRequest(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val method: String,
    val url: String,
    val headers: String,
    val body: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val statusCode: Int = 0,
    val responseHeaders: String = "",
    val responseBody: String = ""
)
