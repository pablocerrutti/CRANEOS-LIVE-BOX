package com.craneos.livebox

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbConstants
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Build

data class UsbCameraInfo(
    val name: String,
    val vendorId: Int,
    val productId: Int,
    val interfaceCount: Int,
    val permissionState: String
)

/**
 * Detects USB Video Class interfaces and requests Android USB permission.
 * Detection is not video capture: a UVC streaming backend must be added separately.
 */
class UsbCameraMonitor(
    private val context: Context,
    private val onChanged: (List<UsbCameraInfo>) -> Unit
) {
    private val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
    private val permissionAction = "${context.packageName}.USB_PERMISSION"
    private var running = false

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(ctx: Context, intent: Intent) {
            when (intent.action) {
                UsbManager.ACTION_USB_DEVICE_ATTACHED,
                UsbManager.ACTION_USB_DEVICE_DETACHED -> scan()
                permissionAction -> scan(requestMissingPermission = false)
            }
        }
    }

    fun start() {
        if (running) return
        running = true
        val filter = IntentFilter().apply {
            addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
            addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
            addAction(permissionAction)
        }
        if (Build.VERSION.SDK_INT >= 33) {
            context.registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            @Suppress("DEPRECATION")
            context.registerReceiver(receiver, filter)
        }
        scan()
    }

    fun stop() {
        if (!running) return
        running = false
        try { context.unregisterReceiver(receiver) } catch (_: IllegalArgumentException) { }
    }

    private fun isUvc(device: UsbDevice): Boolean {
        return (0 until device.interfaceCount).any { index ->
            val intf = device.getInterface(index)
            intf.interfaceClass == 14 &&
                (intf.interfaceSubclass == 1 || intf.interfaceSubclass == 2)
        }
    }

    private fun scan(requestMissingPermission: Boolean = true) {
        val cameras = usbManager.deviceList.values
            .filter(::isUvc)
            .map { device ->
                val uvcInterfaces = (0 until device.interfaceCount)
                    .map { device.getInterface(it) }
                    .count { it.interfaceClass == 14 && (it.interfaceSubclass == 1 || it.interfaceSubclass == 2) }
                val granted = usbManager.hasPermission(device)
                if (!granted && requestMissingPermission) requestPermission(device)
                UsbCameraInfo(
                    name = device.productName ?: device.deviceName,
                    vendorId = device.vendorId,
                    productId = device.productId,
                    interfaceCount = uvcInterfaces,
                    permissionState = if (granted) "Autorizada" else "Esperando autorización"
                )
            }
        onChanged(cameras)
    }

    private fun requestPermission(device: UsbDevice) {
        val intent = Intent(permissionAction).setPackage(context.packageName)
        val pendingIntent = android.app.PendingIntent.getBroadcast(
            context,
            device.deviceId,
            intent,
            android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_MUTABLE
        )
        usbManager.requestPermission(device, pendingIntent)
    }
}
