package com.interceptor.proxy.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RequestDetailPanel(requestId: String) {
    var isEditMode by remember { mutableStateOf(false) }
    var selectedTabIndex by remember { mutableStateOf(0) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Header with edit button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Request Details", style = MaterialTheme.typography.headlineSmall)
            IconButton(onClick = { isEditMode = !isEditMode }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = if (isEditMode) "Done" else "Edit"
                )
            }
        }

        // Request/Response tabs
        TabRow(selectedTabIndex = selectedTabIndex) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 },
                text = { Text("Request") }
            )
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 },
                text = { Text("Response") }
            )
        }

        when (selectedTabIndex) {
            0 -> RequestSection(isEditMode = isEditMode)
            1 -> ResponseSection(isEditMode = isEditMode)
        }

        if (isEditMode) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { /* Send request */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Send")
                }
                OutlinedButton(
                    onClick = { isEditMode = false },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}

@Composable
fun RequestSection(isEditMode: Boolean) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Method & URL", style = MaterialTheme.typography.titleSmall)
                if (isEditMode) {
                    TextField(
                        value = "GET",
                        onValueChange = {},
                        label = { Text("Method") },
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    Text("GET", style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        Card {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Headers", style = MaterialTheme.typography.titleSmall)
                if (isEditMode) {
                    TextField(
                        value = "Host: api.example.com\nUser-Agent: Android",
                        onValueChange = {},
                        label = { Text("Headers") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp),
                        maxLines = 5
                    )
                } else {
                    Text("Host: api.example.com", style = MaterialTheme.typography.bodySmall)
                    Text("User-Agent: Android", style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        Card {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Body", style = MaterialTheme.typography.titleSmall)
                if (isEditMode) {
                    TextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("Body (optional)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp),
                        maxLines = 5
                    )
                } else {
                    Text("No body", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
fun ResponseSection(isEditMode: Boolean) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Status", style = MaterialTheme.typography.titleSmall)
                Text("200 OK", style = MaterialTheme.typography.bodySmall)
            }
        }

        Card {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Headers", style = MaterialTheme.typography.titleSmall)
                if (isEditMode) {
                    TextField(
                        value = "Content-Type: application/json\nContent-Length: 256",
                        onValueChange = {},
                        label = { Text("Headers") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp),
                        maxLines = 5
                    )
                } else {
                    Text("Content-Type: application/json", style = MaterialTheme.typography.bodySmall)
                    Text("Content-Length: 256", style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        Card {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Body", style = MaterialTheme.typography.titleSmall)
                if (isEditMode) {
                    TextField(
                        value = "{\"status\":\"success\", \"data\":[]}",
                        onValueChange = {},
                        label = { Text("Body") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp),
                        maxLines = 5
                    )
                } else {
                    Text(
                        text = "{\"status\":\"success\", \"data\":[]}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
