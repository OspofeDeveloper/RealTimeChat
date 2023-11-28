package com.example.realtimechat.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.realtimechat.R
import com.example.realtimechat.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
    }

    private fun initUI() {
        initUIState()
        initListeners()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is HomeViewState.LOADING -> { binding.pbLoading.isVisible = true }
                        is HomeViewState.REGISTERED -> { findNavController().navigate(R.id.action_home_fragment_to_chat_fragment) }
                        is HomeViewState.UNREGISTERED -> { binding.pbLoading.isVisible = false }
                    }
                }
            }
        }
    }

    private fun initListeners() {
        binding.btnChat.setOnClickListener {
            if(!binding.tietName.text.isNullOrEmpty()) {
                saveNickName(binding.tietName.text.toString())
                findNavController().navigate(R.id.action_home_fragment_to_chat_fragment)
            }
        }
    }

    private fun saveNickName(userName: String) {
        viewModel.saveUserName(userName)
    }

}