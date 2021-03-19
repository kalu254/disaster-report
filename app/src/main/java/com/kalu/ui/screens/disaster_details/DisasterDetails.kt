package com.kalu.ui.screens.disaster_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.kalu.models.Disaster
import com.kalu.ui.adapters.PagerAdapter
import com.kalu.ui.databinding.FragmentDisasterDetailsBinding
import com.kalu.ui.screens.view_model_factories.DetailViewModelFactory
import kotlinx.android.synthetic.main.fragment_disaster_details.*

class DisasterDetails : Fragment() {

    private lateinit var disaster: Disaster;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDisasterDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        disaster = DisasterDetailsArgs.fromBundle(requireArguments()).selectedDisaster
        val viewModelFactory = DetailViewModelFactory(disaster,application)
        binding.viewModel = ViewModelProvider(this,viewModelFactory).get(DisasterDetailViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pager_disaster_images.adapter = PagerAdapter(disaster)
    }


}
