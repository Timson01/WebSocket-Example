package space.timur.echowebsocket.data.repository

import space.timur.echowebsocket.common.WebSocketCallback
import space.timur.echowebsocket.data.remote.WebSocketApi
import space.timur.echowebsocket.domain.repository.WebSocketRepository

class WebSocketRepositoryImpl(
    private val webSocketApi: WebSocketApi
) : WebSocketRepository {

    override fun connectWebSocket(callback: WebSocketCallback) {
        webSocketApi.connectWebSocket(callback)
    }

    override fun sendMessage(message: String) {
        webSocketApi.sendMessage(message)
    }

    override fun disconnectWebSocket() {
        webSocketApi.disconnectWebSocket()
    }

}