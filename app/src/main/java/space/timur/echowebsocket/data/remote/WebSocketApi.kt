package space.timur.echowebsocket.data.remote

import android.util.Log
import okhttp3.*
import okio.ByteString.Companion.decodeHex
import space.timur.echowebsocket.common.WebSocketCallback

class WebSocketApi(
    private val client: OkHttpClient,
    private val request: Request
) {

    private lateinit var webSocket: WebSocket

    fun connectWebSocket(callback: WebSocketCallback) {
        webSocket = client.newWebSocket(request, EchoWebSocketListener(callback))
    }

    fun sendMessage(message: String) {
        webSocket.send(message)
    }

    fun disconnectWebSocket() {
        webSocket.cancel()
    }

    private class EchoWebSocketListener(private val callback: WebSocketCallback) : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            webSocket.send("Hello, it's Tima!")
            webSocket.send("What's up?")
            webSocket.send("deadbeef".decodeHex())
            callback.onWebSocketOpen()
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            output("Receiving : $text")
            callback.onMessageReceived(text)
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null)
            output("Closing : $code / $reason")
            callback.onWebSocketClosed()
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            output("Error : " + t.message)
            callback.onWebSocketFailure(t)
        }

        companion object {
            private val NORMAL_CLOSURE_STATUS = 1000
        }

        private fun output(txt: String) {
            Log.v("WSS", txt)
        }
    }

}