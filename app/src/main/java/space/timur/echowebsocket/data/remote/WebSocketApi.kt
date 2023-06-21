package space.timur.echowebsocket.data.remote

import android.util.Log
import okhttp3.*
import okio.ByteString.Companion.decodeHex

class WebSocketApi(
    private val client: OkHttpClient,
    private val request: Request
) {

    private lateinit var webSocket: WebSocket

    fun connectWebSocket() {
        webSocket = client.newWebSocket(request, EchoWebSocketListener())
    }

    fun sendMessage(message: String) {
        webSocket.send(message)
    }

    fun disconnectWebSocket() {
        webSocket.cancel()
    }

    private class EchoWebSocketListener : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            webSocket.send("Hello, it's Tima!")
            webSocket.send("What's up?")
            webSocket.send("deadbeef".decodeHex())
            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            output("Receiving : $text")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null)
            output("Closing : $code / $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            output("Error : " + t.message)
        }

        companion object {
            private val NORMAL_CLOSURE_STATUS = 1000
        }

        private fun output(txt: String) {
            Log.v("WSS", txt)
        }
    }

}