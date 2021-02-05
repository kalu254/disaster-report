package com.kalu.disaster_alarm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kalu.disaster_alarm.databinding.FragmentDisasterFeedBinding
import com.kalu.disaster_alarm_view_models.DisasterFeedViewModel
import com.kalu.models.Disaster
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DisasterFeedFragment : Fragment() {

    private lateinit var disasterVM: DisasterFeedViewModel
    private lateinit var binding: FragmentDisasterFeedBinding
    private val disasterFeedJob = Job()
    private lateinit var allDisasters: LiveData<List<Disaster>>
    private lateinit var adapter : DisasterFeedAdapter

    private val disasterFeedScope = CoroutineScope(Dispatchers.Main + disasterFeedJob)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.fragment_disaster_feed,
            container,
            false
        ) as FragmentDisasterFeedBinding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disasterVM = ViewModelProvider(this).get(DisasterFeedViewModel::class.java)
        fetchDisasters()
    }


    private fun fetchDisasters() {
        disasterFeedScope.launch {
            allDisasters = disasterVM.getAllDisasters()
            adapter = DisasterFeedAdapter()
            val manager = GridLayoutManager(activity,3)
            allDisasters.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it)
                binding.recyclerDisaster.layoutManager = manager
                binding.recyclerDisaster.adapter = adapter
                Log.d("allDisasters",allDisasters.value.toString())
            })


        }
    }


    override fun onStop() {
        super.onStop()
        disasterFeedJob.cancel()
    }

}
