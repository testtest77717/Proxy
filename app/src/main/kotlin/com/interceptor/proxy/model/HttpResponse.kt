package com.interceptor.proxy.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "http_responses",
    foreignKeys = [
        ForeignKey(
            entity = HttpRequest::class,
            parentColumns = ["id"],
            childColumns = ["requestId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HttpResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val requestId: Int,
    val statusCode: Int,
    val statusMessage: String,
    val headers: String,
    val body: String,
    val timestamp: Long = System.currentTimeMillis()
)
