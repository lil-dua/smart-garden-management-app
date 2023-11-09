package com.advanced.smartgardenapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.advanced.smartgardenapp.R
import com.advanced.smartgardenapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            loginFragment = this@LoginFragment
        }
    }

    fun login() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    fun goToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

}