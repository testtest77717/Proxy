package com.interceptor.proxy.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.interceptor.proxy.ui.component.InterceptorTab
import com.interceptor.proxy.ui.component.RequestHistoryList
import com.interceptor.proxy.ui.component.RequestDetailPanel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var isIntercepting by remember { mutableStateOf(false) }
    var selectedTabIndex by remember { mutableStateOf(0) }
    var selectedRequestId by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("HTTP Interceptor") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Button(
                    onClick = { isIntercepting = !isIntercepting },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isIntercepting) 
                            MaterialTheme.colorScheme.error 
                        else 
                            MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        imageVector = if (isIntercepting) Icons.Filled.Stop else Icons.Filled.PlayArrow,
                        contentDescription = if (isIntercepting) "Stop" else "Start",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(if (isIntercepting) "Stop Interceptor" else "Start Interceptor")
                }
                Spacer(modifier = Modifier.width(8.dp))
                StatusIndicator(isIntercepting = isIntercepting, modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Tab Navigation
            TabRow(selectedTabIndex = selectedTabIndex) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 },
                    text = { Text("History") }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 },
                    text = { Text("Details") }
                )
                Tab(
                    selected = selectedTabIndex == 2,
                    onClick = { selectedTabIndex = 2 },
                    text = { Text("Settings") }
                )
            }

            // Tab Content
            when (selectedTabIndex) {
                0 -> RequestHistoryList(
                    onRequestSelected = { selectedRequestId = it },
                    selectedRequestId = selectedRequestId
                )
                1 -> if (selectedRequestId != null) {
                    RequestDetailPanel(requestId = selectedRequestId!!)
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Select a request to view details")
                    }
                }
                2 -> SettingsScreen()
            }
        }
    }
}

@Composable
fun StatusIndicator(isIntercepting: Boolean, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier.size(12.dp),
            color = if (isIntercepting) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
            shape = MaterialTheme.shapes.small
        ) {}
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = if (isIntercepting) "Active" else "Inactive",
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Settings", style = MaterialTheme.typography.headlineSmall)
        
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Proxy Configuration", style = MaterialTheme.typography.titleSmall)
                Text("Port: 8080", style = MaterialTheme.typography.bodySmall)
                Text("Protocol: HTTP/HTTPS", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
