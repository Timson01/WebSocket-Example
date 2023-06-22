package space.timur.echowebsocket.domain.repository

import space.timur.echowebsocket.common.WebSocketCallback

interface WebSocketRepository {

    fun connectWebSocket(callback: WebSocketCallback)

    fun sendMessage(message: String)

    fun disconnectWebSocket()

}