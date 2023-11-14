package com.advanced.smartgardenapp.ui.register

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.advanced.smartgardenapp.data.model.User
import com.advanced.smartgardenapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            registerFragment = this@RegisterFragment

            viewModel = ViewModelProvider(this@RegisterFragment)[RegisterViewModel::class.java]

        }
    }

    fun register() {
        if (checkValidInputDetail()) {
            //assign input user value
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()
            val user = User(email = email,password = password)

            //register user with input
            viewModel.registerUser(user)
            Toast.makeText(requireContext(),"New user was created!",Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    fun backToLogin() {
        findNavController().popBackStack()
    }

    private fun checkValidInputDetail(): Boolean {
        if (binding.inputEmail.text.toString().isEmpty()){
            Toast.makeText(requireContext(),"Please enter email!",Toast.LENGTH_SHORT).show()
            return false
        }else if (binding.inputPassword.text.toString().isEmpty()) {
            Toast.makeText(requireContext(),"Please enter password!",Toast.LENGTH_SHORT).show()
            return false
        }else if(binding.inputConfirmPassword.text.toString().isEmpty()) {
            Toast.makeText(requireContext(),"Please enter confirm password!",Toast.LENGTH_SHORT).show()
            return false
        }else if(binding.inputPassword.text.toString() != binding.inputConfirmPassword.text.toString()) {
            Toast.makeText(requireContext(),"Password and Confirm password not matched!",Toast.LENGTH_SHORT).show()
            return false
        }else if(!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.text.toString()).matches()) {
            Toast.makeText(requireContext(),"Please enter a email!",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}