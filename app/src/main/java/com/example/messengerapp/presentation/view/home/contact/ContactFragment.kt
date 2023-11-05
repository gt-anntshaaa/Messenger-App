package com.example.messengerapp.presentation.view.home.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.messengerapp.R
import com.example.messengerapp.data.model.users.Contact
import com.example.messengerapp.databinding.FragmentContactBinding
import com.example.messengerapp.presentation.common.adapter.ContactAdapter
import com.example.messengerapp.presentation.common.resource.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : Fragment() {
    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    private val contactViewModel: ContactViewModel by viewModels()
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contactAdapter = ContactAdapter { item -> doClick(item) }

        binding.rvContact.adapter = contactAdapter

        contactViewModel.allContact.observe(viewLifecycleOwner, Observer {
            when(it){
                is UIState.Loading -> {}
                is UIState.Success -> {
                    contactAdapter.submit(it.data.second)
                }
                is UIState.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()}

                else -> {}
            }
        })

        contactViewModel.loadContact()


    }

    private fun doClick(item: Contact) {
        Toast.makeText(requireContext(), item.name, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}