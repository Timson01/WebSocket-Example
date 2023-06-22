package space.timur.echowebsocket.common

sealed class WebSocketResult<T> (val data: T? = null) {
    class WebSocketOpen<T>: WebSocketResult<T>()
    class WebSocketMessageReceived<T>(data: T? = null): WebSocketResult<T>(data)
    class WebSocketClosed<T>: WebSocketResult<T>()
    class WebSocketFailure<T>(data: T? = null): WebSocketResult<T>(data)
}