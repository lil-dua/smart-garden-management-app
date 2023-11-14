package com.advanced.smartgardenapp.ui.login

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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

            viewModel = ViewModelProvider(this@LoginFragment)[LoginViewModel::class.java]

        }
    }

    fun login() {
        if (checkValidInputDetail()) {
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()

            viewModel.authenticatorUser(email,password) { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(requireContext(),"Welcome back!",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }else {
                    Toast.makeText(requireContext(),"Email or password was not exits!",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun goToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun checkValidInputDetail(): Boolean {
        if (binding.inputEmail.text.toString().isEmpty()){
            Toast.makeText(requireContext(),"Please enter email!",Toast.LENGTH_SHORT).show()
            return false
        }else if (binding.inputPassword.text.toString().isEmpty()) {
            Toast.makeText(requireContext(),"Please enter password!",Toast.LENGTH_SHORT).show()
            return false
        }else if(!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.text.toString()).matches()) {
            Toast.makeText(requireContext(),"Please enter a email!",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}