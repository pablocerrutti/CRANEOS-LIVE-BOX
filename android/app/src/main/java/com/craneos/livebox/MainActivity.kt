package com.craneos.livebox

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    private lateinit var status: TextView
    private lateinit var devices: TextView
    private lateinit var monitor: UsbCameraMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 48, 32, 32)
        }
        status = TextView(this).apply { textSize = 20f; text = "Buscando cámaras USB…" }
        devices = TextView(this).apply { textSize = 15f }
        root.addView(status)
        root.addView(devices)
        setContentView(ScrollView(this).apply { addView(root) })

        monitor = UsbCameraMonitor(this) { found ->
            runOnUiThread {
                status.text = if (found.isEmpty()) "No hay cámaras UVC detectadas" else "Cámaras USB detectadas: ${found.size}"
                devices.text = found.joinToString("\n\n") { camera ->
                    "${camera.name}\nVID: ${camera.vendorId} · PID: ${camera.productId}\nInterfaces UVC: ${camera.interfaceCount}\nEstado: ${camera.permissionState}"
                }
            }
        }
        monitor.start()
    }

    override fun onDestroy() {
        if (::monitor.isInitialized) monitor.stop()
        super.onDestroy()
    }
}
