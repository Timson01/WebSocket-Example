package space.timur.echowebsocket.common

interface WebSocketCallback {

    fun onWebSocketOpen()
    fun onMessageReceived(message: String)
    fun onWebSocketClosed()
    fun onWebSocketFailure(throwable: Throwable)

}