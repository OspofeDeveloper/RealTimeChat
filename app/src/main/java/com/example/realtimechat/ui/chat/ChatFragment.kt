package com.example.realtimechat.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realtimechat.R
import com.example.realtimechat.databinding.FragmentChatBinding
import com.example.realtimechat.ui.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ChatViewModel>()

    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initUIState()
        initListeners()
        setupToolbar()
        setUpMessages()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    /** Llamamos a setupToolbar aqui porque asi tenemos el nombre siempre */
                    setupToolbar()
                    chatAdapter.updateList(it.toMutableList(), viewModel.name)
                    binding.rvChatMsg.scrollToPosition(chatAdapter.messageList.size - 1)
                }
            }
        }
    }

    private fun initListeners() {
        initBackListener()
        initLogOutListener()
        initSendMsgListener()
    }


    /** En este caso necesitamos hacer la navegación una vez se complete la corrutina de
     *  logOut, por lo tanto pasamos la instrucción de navegación a logOut y la ejecutaremos
     *  una vez acabada la corrutina */
    private fun initBackListener() {
        binding.ivBack.setOnClickListener {
            viewModel.logOut { findNavController().navigate(R.id.action_back) }
        }
    }

    private fun initLogOutListener() {
        binding.ivLogOut.setOnClickListener {
            viewModel.logOut { findNavController().navigate(R.id.action_back) }
        }
    }

    private fun initSendMsgListener() {
        binding.btnSendMsg.setOnClickListener {
            val msg = binding.etChat.text.toString()

            if(msg.isNotEmpty()) {
                viewModel.sendMessage(msg)
            }
            binding.etChat.text.clear()
        }
    }

    private fun setupToolbar() {
        binding.tvTitle.text = viewModel.name
    }

    private fun setUpMessages() {
        chatAdapter = ChatAdapter(mutableListOf())
        binding.rvChatMsg.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}