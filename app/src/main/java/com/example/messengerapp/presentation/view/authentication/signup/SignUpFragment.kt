package com.example.messengerapp.presentation.view.authentication.signup

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.messengerapp.databinding.FragmentSignUpBinding
import com.example.messengerapp.presentation.common.resource.UIState
import com.example.messengerapp.presentation.common.util.hide
import com.example.messengerapp.presentation.common.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by viewModels()
    //private var uriImage: Uri? = null

    // open image on galery and pick images
    private val imagePicker = registerForActivityResult(ActivityResultContracts.GetContent()){
        if (it != null){
            signUpViewModel.selectedProfileImage(it)
//            binding.imgProfile.setImageURI(it)
//            uriImage = it
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUI()

        // Setup listener button
        binding.imgProfile.setOnClickListener { onPickImage() }
        binding.signupButton.setOnClickListener { onSignUp() }
    }

    private fun observeUI() {
        signUpViewModel.imagePicked.observe(viewLifecycleOwner, Observer { uri ->
            if (uri != null){
                binding.imgProfile.setImageURI(uri)
            }
        })
        signUpViewModel.user.observe(viewLifecycleOwner, Observer {
            when (it){
                is UIState.Loading -> {
                    binding.loading.show()
                }
                is UIState.Success -> {
                    binding.loading.hide()
                    Toast.makeText(requireContext(), it.data, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(SignUpFragmentDirections.actionGlobalHomeNavigation())
                }
                is UIState.Failure -> {
                    binding.loading.hide()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()}

                null -> {}
            }
        })
    }

    private fun onPickImage() {
        imagePicker.launch("image/*")
    }

    private fun onSignUp() {
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()
        val name = binding.inputName.text.toString()
        val urlImage = signUpViewModel.imagePicked.value
        signUpViewModel.doSignUpAndCreateProfile(email, password, name, urlImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}