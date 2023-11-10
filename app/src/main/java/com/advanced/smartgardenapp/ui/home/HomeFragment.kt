package com.advanced.smartgardenapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.advanced.smartgardenapp.data.model.Datasource
import com.advanced.smartgardenapp.databinding.FragmentHomeBinding
import com.advanced.smartgardenapp.ui.home.garden.GardenAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: GardenAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeFragment = this@HomeFragment

            viewModel = ViewModelProvider(this@HomeFragment)[HomeViewModel::class.java]

            val data = Datasource().loadGarden()
            adapter = GardenAdapter(data,findNavController())
            binding.recycleViewGarden.adapter = adapter

            viewModel.humidity.observe(viewLifecycleOwner) { humidity ->
                binding.textActualHumidity.text = "$humidity%"
            }
            viewModel.temperature.observe(viewLifecycleOwner) {
                binding.textActualTemperature.text = "$it°c"
            }

            viewModel.fetchActualHumidityValue()
            viewModel.fetchActualTemperatureValue()
        }
    }

}