package com.example.realtimechat.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.realtimechat.databinding.ItemChatMeBinding
import com.example.realtimechat.databinding.ItemChatOtherBinding
import com.example.realtimechat.domain.model.MessageModel

class ChatAdapter(
    var messageList: MutableList<MessageModel>,
    private var userName: String = ""
) : RecyclerView.Adapter<ChatViewHolder>() {

    companion object {
        const val SENT_MESSAGE = 0
        const val RECEIVED_MESSAGE = 1
    }

    fun updateList(list: MutableList<MessageModel>, name: String) {
        userName = name
        messageList.clear()
        messageList.addAll(list)
        notifyItemInserted(messageList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
         val binding = when (viewType) {
            SENT_MESSAGE -> {
                ItemChatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }

            RECEIVED_MESSAGE -> {
                ItemChatOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }

            else -> {
                ItemChatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
        }
        return ChatViewHolder(binding)
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(messageList[position], getItemViewType(position))
    }

    /** Sobreescribimos esta función porque necesitamos saber que tipo de vista vamos a tener
     *  que pintar dependiendo de si el mensaje lo mandamos nosotros o nos lo mandan */
    override fun getItemViewType(position: Int): Int {
        return if (messageList[position].user.userName == userName) {
            SENT_MESSAGE
        } else {
            RECEIVED_MESSAGE
        }
    }

}