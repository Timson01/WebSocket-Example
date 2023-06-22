package space.timur.echowebsocket.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.timur.echowebsocket.R

class ChatAdapter(private val inflater: LayoutInflater): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val messages = mutableListOf<String>()

    companion object {
        private const val TYPE_MESSAGE_SENT = 0
        private const val TYPE_MESSAGE_RECEIVED = 1
    }

    inner class SentMessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageTxt: TextView = itemView.findViewById(R.id.sentTxt)

        fun bind(message: String) {
            messageTxt.text = message
        }
    }

    inner class ReceivedMessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageTxt: TextView = itemView.findViewById(R.id.receivedTxt)

        fun bind(message: String) {
            messageTxt.text = message
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) {
                TYPE_MESSAGE_RECEIVED
            } else {
                TYPE_MESSAGE_SENT
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View = when(viewType){
            TYPE_MESSAGE_SENT -> inflater.inflate(R.layout.item_sent_message, parent, false)
            TYPE_MESSAGE_RECEIVED -> inflater.inflate(R.layout.item_received_message, parent, false)
            else -> throw IllegalArgumentException("Invalid view type")
        }
        return when (viewType) {
            TYPE_MESSAGE_SENT -> SentMessageHolder(view)
            TYPE_MESSAGE_RECEIVED -> ReceivedMessageHolder(view)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        if (position % 2 == 0) {
            val receivedMessageHolder = holder as ReceivedMessageHolder
            receivedMessageHolder.bind(message)
        } else {
            val sentMessageHolder = holder as SentMessageHolder
            sentMessageHolder.bind(message)
        }
    }

    override fun getItemCount(): Int = messages.size

    @SuppressLint("NotifyDataSetChanged")
    fun addMessage(message: String){
        messages.add(message)
        notifyDataSetChanged()
    }

}