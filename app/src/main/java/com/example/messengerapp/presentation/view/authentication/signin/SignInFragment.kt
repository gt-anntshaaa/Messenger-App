package com.example.messengerapp.presentation.view.authentication.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.messengerapp.databinding.FragmentSignInBinding
import com.example.messengerapp.presentation.common.resource.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: SignInViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUI()

        binding.createAccount.setOnClickListener { onCreateAccount() }
        binding.loginButton.setOnClickListener { onSignIn() }
    }

    private fun observeUI() {
        loginViewModel.user.observe(viewLifecycleOwner, Observer {
            when(it){
                is UIState.Loading -> {}
                is UIState.Success -> {
                    Toast.makeText(requireContext(), it.data.toString(), Toast.LENGTH_SHORT).show()
                    findNavController().navigate(SignInFragmentDirections.actionGlobalHomeNavigation())
                }
                is UIState.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()}

                else -> {}
            }
        })
    }

    private fun onSignIn() {
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()
        loginViewModel.doSignIn(email, password)
        //findNavController().navigate(SignInFragmentDirections.actionGlobalHomeNavigation())
    }

    private fun onCreateAccount() {
        findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}