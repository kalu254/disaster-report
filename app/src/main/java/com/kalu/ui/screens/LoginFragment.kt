package com.kalu.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kalu.models.UserAuthorization
import com.kalu.network.Resource
import com.kalu.network.UserAuthApi
import com.kalu.repository.AuthRepository
import com.kalu.ui.R
import com.kalu.ui.base.BaseFragment
import com.kalu.ui.databinding.FragmentLoginBinding
import com.kalu.ui.enable
import com.kalu.ui.hideKeyboard
import com.kalu.ui.view_models.AuthViewModel
import com.kalu.ui.visible


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding,AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)

        binding.btnLogIn.enable(false)

        binding.progressCircularLoggingIn.visible(false)

        binding.btnLogIn.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val userAuthorization = UserAuthorization(email,password)
            viewModel.login(userAuthorization)
            binding.btnLogIn.enable(false)
            it.hideKeyboard()
        }
        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                        viewModel.saveAuthToken(it.value.accessToken)
                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                        binding.progressCircularLoggingIn.visible(false)
                }
                else -> binding.progressCircularLoggingIn.visible(true)

            }
        })

        binding.editTextPassword.addTextChangedListener {
            val email = binding.editTextEmail.text.toString().trim()
            if (it != null) {
                binding.btnLogIn.enable(email.isNotEmpty() && it.trim().isNotEmpty())
            }
        }


    }


    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(UserAuthApi::class.java),userPreferences)


}

