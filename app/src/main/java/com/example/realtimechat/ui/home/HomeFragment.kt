package com.example.realtimechat.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.realtimechat.R
import com.example.realtimechat.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnChat.setOnClickListener {
            if(!binding.tietName.text.isNullOrEmpty()) {
                findNavController().navigate(R.id.action_home_fragment_to_chat_fragment)
            }
        }

        return binding.root
    }

}