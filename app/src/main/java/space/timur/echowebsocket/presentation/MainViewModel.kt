package space.timur.echowebsocket.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import space.timur.echowebsocket.common.WebSocketCallback
import space.timur.echowebsocket.common.WebSocketResult
import space.timur.echowebsocket.domain.repository.WebSocketRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val webSocketRepository: WebSocketRepository
) : ViewModel(), WebSocketCallback {

    private val _webSocketResult = MutableLiveData<WebSocketResult<String>>()
    val webSocketResult: LiveData<WebSocketResult<String>> = _webSocketResult

    fun connectWebSocket(){
        webSocketRepository.connectWebSocket(this)
    }

    fun sendMessage(message: String){
        webSocketRepository.sendMessage(message)
    }

    fun disconnectWebSocket(){
        webSocketRepository.disconnectWebSocket()
    }

    override fun onWebSocketOpen() {
        _webSocketResult.value = WebSocketResult.WebSocketOpen()
    }

    override fun onMessageReceived(message: String) {
        _webSocketResult.value = WebSocketResult.WebSocketMessageReceived(message)
    }

    override fun onWebSocketClosed() {
        _webSocketResult.value = WebSocketResult.WebSocketClosed()
    }

    override fun onWebSocketFailure(throwable: Throwable) {
        _webSocketResult.value = WebSocketResult.WebSocketFailure(throwable.message)
    }


}