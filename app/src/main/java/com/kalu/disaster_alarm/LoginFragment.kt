package com.kalu.disaster_alarm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.kalu.disaster_alarm.databinding.FragmentDisasterFeedBinding
import com.kalu.disaster_alarm.databinding.FragmentLoginBinding
import com.kalu.disaster_alarm_view_models.UserAuthViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }

    private val userAuthViewModel : UserAuthViewModel by activityViewModels()
    private  var savedStateHandle: SavedStateHandle? = null
    private lateinit var binding: FragmentLoginBinding



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        ) as FragmentLoginBinding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        savedStateHandle?.set(LOGIN_SUCCESSFUL, false)
        binding.cirLoginButton.setOnClickListener {
            binding.pbLoggingIn.visibility = View.VISIBLE
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            userAuthViewModel.authenticateUser(email,password).observe(viewLifecycleOwner, Observer { authenticated ->
                if (authenticated){
                    savedStateHandle?.set(LOGIN_SUCCESSFUL,true)
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                    binding.pbLoggingIn.visibility = View.INVISIBLE

                }
            })
        }



    }


    }

