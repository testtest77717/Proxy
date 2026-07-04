package com.interceptor.proxy.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RequestHistoryList(
    onRequestSelected: (String) -> Unit,
    selectedRequestId: String?
) {
    // Mock data - replace with real data from database
    val mockRequests = listOf(
        RequestItem("1", "GET", "https://api.example.com/users", 200, "2.5s"),
        RequestItem("2", "POST", "https://api.example.com/login", 200, "1.2s"),
        RequestItem("3", "GET", "https://cdn.example.com/image.jpg", 404, "3.1s"),
        RequestItem("4", "PUT", "https://api.example.com/profile", 200, "1.8s"),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(mockRequests) { request ->
            RequestHistoryItem(
                request = request,
                isSelected = request.id == selectedRequestId,
                onClick = { onRequestSelected(request.id) }
            )
        }
    }
}

@Composable
fun RequestHistoryItem(
    request: RequestItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) 
        MaterialTheme.colorScheme.primaryContainer 
    else 
        MaterialTheme.colorScheme.surface

    val statusColor = when (request.statusCode) {
        200, 201, 204 -> Color.Green
        300, 301, 302 -> Color.Yellow
        400, 401, 403, 404 -> Color(0xFFFF9800)
        500, 502, 503 -> Color.Red
        else -> Color.Gray
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Method badge
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = request.method,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            // URL and details
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = request.url,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Status: ${request.statusCode}",
                        style = MaterialTheme.typography.labelSmall,
                        color = statusColor
                    )
                    Text(
                        text = request.duration,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}

data class RequestItem(
    val id: String,
    val method: String,
    val url: String,
    val statusCode: Int,
    val duration: String
)
