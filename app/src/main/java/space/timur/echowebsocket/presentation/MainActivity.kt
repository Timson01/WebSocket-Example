package space.timur.echowebsocket.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import space.timur.echowebsocket.R
import space.timur.echowebsocket.common.WebSocketResult
import kotlinx.coroutines.flow.collect
import space.timur.echowebsocket.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: ChatAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        adapter = ChatAdapter(layoutInflater)
        val manager = LinearLayoutManager(this)

        binding.apply {

            recyclerView.adapter = adapter
            recyclerView.layoutManager = manager

            sendBtn.setOnClickListener {
                viewModel.sendMessage(messageEdit.text.toString())
                adapter.addMessage(messageEdit.text.toString())
                messageEdit.setText("")
            }

        }
        viewModel.connectWebSocket()
        initObservers()
    }

    override fun onPause() {
        super.onPause()
        viewModel.disconnectWebSocket()
    }

    private fun initObservers() {
        viewModel.webSocketResult.observe(this, Observer {
            when(it){
                is WebSocketResult.WebSocketOpen -> {
                    Toast.makeText(this@MainActivity, "WebSocket Connected", Toast.LENGTH_SHORT).show()
                }
                is WebSocketResult.WebSocketMessageReceived -> {
                    adapter.addMessage(it.data.toString())
                }
                is WebSocketResult.WebSocketClosed -> {
                    Toast.makeText(this@MainActivity, "WebSocket Closed", Toast.LENGTH_SHORT).show()
                }
                is WebSocketResult.WebSocketFailure -> {
                    Toast.makeText(this@MainActivity, it.data.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}