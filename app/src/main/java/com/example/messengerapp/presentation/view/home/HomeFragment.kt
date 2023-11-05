package com.example.messengerapp.presentation.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import com.example.messengerapp.R
import com.example.messengerapp.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val authenticationViewModel: AuthenticationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        authenticationViewModel.user.observe(viewLifecycleOwner, Observer {user ->
            if (user != null){
                updateUI(user)
            }else{
                val navOptions = NavOptions.Builder().setPopUpTo(R.id.homeFragment, true).build()
                findNavController().navigate(HomeFragmentDirections.actionGlobalAuthNavigation(), navOptions)
            }
        })

        authenticationViewModel.checkAuthenticationUser()
    }

    private fun updateUI(user: FirebaseUser) {
        setupOptionMenu()


        binding.name.text = user.displayName
        binding.email.text = user.email
        Picasso.get().load(user.photoUrl).into(binding.imgProfile)


        binding.fabContact.setOnClickListener { onContact() }


    }

    private fun onContact() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToContactFragment())
    }

    private fun setupOptionMenu() {
        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                //return menuItem.onNavDestinationSelected(findNavController())
                return when(menuItem.itemId){
                    R.id.signInFragment -> {
                        authenticationViewModel.signOut()
                        findNavController().navigate(HomeFragmentDirections.actionGlobalAuthNavigation())
                        Log.i("tag", "onMenuItemSelected: onClicked")
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}