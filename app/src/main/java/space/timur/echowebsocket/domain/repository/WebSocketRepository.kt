package space.timur.echowebsocket.domain.repository

interface WebSocketRepository {

    fun connectWebSocket()

    fun sendMessage(message: String)

    fun disconnectWebSocket()

}