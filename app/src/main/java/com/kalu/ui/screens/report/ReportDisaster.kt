package com.kalu.ui.screens.report

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.kalu.ui.R
import com.kalu.ui.databinding.FragmentAccountBinding
import com.kalu.ui.databinding.ReportDisasterFragmentBinding

class ReportDisaster : Fragment() {

    companion object {
        fun newInstance() = ReportDisaster()
    }

    private lateinit var viewModel: ReportDisasterViewModel
    private lateinit var binding: ReportDisasterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ReportDisasterFragmentBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReportDisasterViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onResume() {
        super.onResume()
        val disasterTypes = resources.getStringArray(R.array.disaster_types)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.drop_down_item,disasterTypes)
        binding.autoComplete.setAdapter(arrayAdapter)

    }

}
