package space.timur.echowebsocket.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import space.timur.echowebsocket.data.remote.WebSocketApi
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val webSocketApi: WebSocketApi
) : ViewModel() {


}