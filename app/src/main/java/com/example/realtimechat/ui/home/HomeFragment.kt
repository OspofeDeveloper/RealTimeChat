package com.example.realtimechat.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.realtimechat.R
import com.example.realtimechat.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

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
        initListeners()
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