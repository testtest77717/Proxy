package com.interceptor.proxy.network

import android.content.Intent
import android.net.VpnService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VpnInterceptorService : VpnService() {
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        scope.launch {
            startVpn()
        }
        return START_STICKY
    }

    private fun startVpn() {
        val builder = Builder()
        builder.setSession("HTTP Interceptor")
        builder.addAddress("10.0.0.2", 32)
        builder.addRoute("0.0.0.0", 0)
        builder.addDnsServer("8.8.8.8")
        // For HTTPS interception, add certificate management here
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cleanup
    }
}
