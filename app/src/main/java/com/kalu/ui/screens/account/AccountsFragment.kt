package com.kalu.ui.screens.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kalu.repository.UserPreferences
import com.kalu.ui.databinding.FragmentAccountBinding
import com.kalu.ui.screens.view_model_factories.AccountViewModelFactory
import kotlinx.coroutines.launch

class AccountsFragment : Fragment() {

    private lateinit var userPreferences: UserPreferences
    private lateinit var accountsViewModel: AccountsViewModel
    private lateinit var binding: FragmentAccountBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
        val application = requireNotNull(activity).application
        binding = FragmentAccountBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        val viewModelFactory = AccountViewModelFactory(userPreferences,application)
        accountsViewModel = ViewModelProvider(this,viewModelFactory).get(AccountsViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launch {
            accountsViewModel.getProfile()
            binding.viewmodel = accountsViewModel
        }
    }
}
