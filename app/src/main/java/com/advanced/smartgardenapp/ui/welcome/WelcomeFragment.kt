package com.advanced.smartgardenapp.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.advanced.smartgardenapp.R
import com.advanced.smartgardenapp.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            welcomeFragment = this@WelcomeFragment
        }
    }

    fun goToRegister() {
        findNavController().navigate(R.id.action_welcomeFragment_to_registerFragment)
    }

    fun goToLogin() {
        findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
    }

}