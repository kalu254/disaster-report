package com.kalu.ui.screens

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.kalu.network.EndPoints
import com.kalu.network.Resource
import com.kalu.repository.DisasterRepository
import com.kalu.ui.DisasterFeedAdapter
import com.kalu.ui.R
import com.kalu.ui.base.BaseFragment
import com.kalu.ui.databinding.FragmentDisasterFeedBinding
import com.kalu.ui.view_models.DisasterFeedViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first



class DisasterFeedFragment : BaseFragment<DisasterFeedViewModel, FragmentDisasterFeedBinding, DisasterRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = DisasterFeedAdapter()
        lifecycleScope.launch {
            viewModel.getAllDisasters()
        }
        viewModel.disasters.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    adapter.submitList(it.value)
                }
                else -> Toast.makeText(requireContext(), "Nothing", Toast.LENGTH_LONG)
            }
        })
        val manager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerDisaster.layoutManager = manager
        binding.recyclerDisaster.adapter = adapter

    }

    override fun getViewModel() = DisasterFeedViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDisasterFeedBinding = FragmentDisasterFeedBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): DisasterRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(EndPoints::class.java, token)
        return DisasterRepository(api)
    }


}
