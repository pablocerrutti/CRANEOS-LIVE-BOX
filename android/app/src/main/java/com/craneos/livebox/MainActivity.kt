package com.craneos.livebox

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.pedro.common.ConnectChecker
import com.pedro.library.rtmp.RtmpDisplay

class MainActivity : Activity(), ConnectChecker {
    private lateinit var webView: WebView
    private var display: RtmpDisplay? = null
    private var pendingUrl: String? = null
    private val captureRequest = 4102
    private val audioPermissionRequest = 4103

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
             View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        webView = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.mediaPlaybackRequiresUserGesture = false
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()
            addJavascriptInterface(CraneosBridge(), "CraneosAndroid")
            loadUrl("https://pablocerrutti.github.io/CRANEOS-LIVE-BOX/?android=1")
        }
        setContentView(webView)
    }

    inner class CraneosBridge {
        @JavascriptInterface fun startStreaming(url: String) {
            runOnUiThread {
                if (!url.startsWith("rtmp://") && !url.startsWith("rtmps://")) {
                    toast("Ingresá una dirección RTMP o RTMPS válida")
                    return@runOnUiThread
                }
                if (display?.isStreaming == true) return@runOnUiThread
                pendingUrl = url.trim()
                if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), audioPermissionRequest)
                } else {
                    requestScreenCapture()
                }
            }
        }
        @JavascriptInterface fun stopStreaming() { runOnUiThread { stopBroadcast() } }
        @JavascriptInterface fun isAvailable(): Boolean = true
    }

    private fun requestScreenCapture() {
        val manager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        startActivityForResult(manager.createScreenCaptureIntent(), captureRequest)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == audioPermissionRequest) {
            if (grantResults.isNotEmpty() && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                requestScreenCapture()
            } else {
                toast("Se necesita permiso de micrófono para preparar el audio RTMP")
                webView.evaluateJavascript("document.body.classList.remove('broadcast-output')", null)
            }
        }
    }

    @Deprecated("Deprecated by Android; retained for broad device compatibility")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != captureRequest) return
        if (resultCode != RESULT_OK || data == null) {
            webView.evaluateJavascript("window.craneosStreamState && window.craneosStreamState('Permiso de captura cancelado')", null)
            webView.evaluateJavascript("document.body.classList.remove('broadcast-output')", null)
            return
        }
        try {
            val stream = RtmpDisplay(this, true, this)
            stream.setIntentResult(resultCode, data)
            val metrics = resources.displayMetrics
            val width = (metrics.widthPixels / 2) * 2
            val height = (metrics.heightPixels / 2) * 2
            val videoReady = stream.prepareVideo(width, height, 30, 2_000_000, 0, metrics.densityDpi)
            val audioReady = stream.prepareAudio(96 * 1024, 44100, true)
            if (!videoReady || !audioReady) {
                stream.stopStream()
                toast("El codificador no pudo preparar video o audio")
                webView.evaluateJavascript("document.body.classList.remove('broadcast-output')", null)
                return
            }
            display = stream
            stream.startStream(pendingUrl ?: "")
        } catch (e: Exception) {
            toast("No se pudo iniciar RTMP: ${e.message}")
            webView.evaluateJavascript("document.body.classList.remove('broadcast-output')", null)
        }
    }

    private fun stopBroadcast() {
        try { display?.stopStream() } catch (_: Exception) { }
        display = null
        pendingUrl = null
        webView.evaluateJavascript("document.body.classList.remove('broadcast-output')", null)
        webView.evaluateJavascript("window.craneosStreamState && window.craneosStreamState('Emisión detenida')", null)
    }

    private fun toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN && display?.isStreaming == true) {
            stopBroadcast()
            toast("Emisión detenida")
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onConnectionStarted(url: String) { runOnUiThread { streamStatus("Conectando…") } }
    override fun onConnectionSuccess() { runOnUiThread { streamStatus("EN VIVO · RTMP conectado") } }
    override fun onNewBitrate(bitrate: Long) { }
    override fun onConnectionFailed(reason: String) { runOnUiThread { streamStatus("Error RTMP: " + reason) } }
    override fun onDisconnect() { runOnUiThread { streamStatus("Desconectado") } }
    override fun onAuthError() { runOnUiThread { streamStatus("Error de autenticación RTMP") } }
    override fun onAuthSuccess() { runOnUiThread { streamStatus("Autenticación correcta") } }

    private fun streamStatus(message: String) {
        val safe = message.replace("\\", "\\\\").replace("'", "\\'")
        webView.evaluateJavascript("window.craneosStreamState && window.craneosStreamState('$safe')", null)
    }

    override fun onDestroy() {
        stopBroadcast()
        webView.destroy()
        super.onDestroy()
    }
}
