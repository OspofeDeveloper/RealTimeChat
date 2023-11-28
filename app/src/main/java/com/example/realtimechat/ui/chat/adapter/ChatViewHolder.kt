package com.example.realtimechat.ui.chat.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.realtimechat.databinding.ItemChatMeBinding
import com.example.realtimechat.databinding.ItemChatOtherBinding
import com.example.realtimechat.domain.model.MessageModel

class ChatViewHolder(private val binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(messageModel: MessageModel, itemViewType: Int) {
        when(itemViewType) {
            ChatAdapter.SENT_MESSAGE -> bindSentMessage(messageModel)
            ChatAdapter.RECEIVED_MESSAGE -> bindReceivedMessage(messageModel)
        }
    }

    /**
     * Cuando usamos as es porque el objeto binding es de tipo ViewBinding, por lo tanto puede
     * tomar el tipo de cualquier subclase de ViewBinding. Este caso le indicamos que tiene que
     * ser de la subclase ItemChatMeBinding
     */
    private fun bindSentMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatMeBinding

        currentBinding.apply {
            tvDateMe.text = messageModel.date
            tvChatMe.text = messageModel.msg
            tvHour.text = messageModel.hour
        }
    }

    private fun bindReceivedMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatOtherBinding

        currentBinding.apply {
            tvDateOther.text = messageModel.date
            tvChatOther.text = messageModel.msg
            tvNameOther.text = messageModel.user.userName
            tvHour.text = messageModel.hour
        }
    }

}