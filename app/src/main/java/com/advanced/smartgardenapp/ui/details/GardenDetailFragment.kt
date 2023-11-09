package com.advanced.smartgardenapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.advanced.smartgardenapp.data.model.Datasource
import com.advanced.smartgardenapp.data.model.Garden
import com.advanced.smartgardenapp.databinding.FragmentGardenDetailBinding
import com.advanced.smartgardenapp.ui.details.plant.PlantAdapter
import com.advanced.smartgardenapp.utils.Constants

class GardenDetailFragment : Fragment() {
    private lateinit var binding: FragmentGardenDetailBinding
    private lateinit var viewModel: GardenDetailViewModel
    private lateinit var adapter: PlantAdapter
    private lateinit var selectedGardenId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGardenDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            detailFragment = this@GardenDetailFragment

            selectedGardenId = requireArguments().getString(Constants.KEY_GARDEN_ID).toString()
            val data = Datasource().loadGarden()
            val garden: Garden? = data.find { it.id == selectedGardenId }
            if (garden != null) {
                selectedGarden = garden
            }

            val dataPlant = Datasource().loadPlants()
            adapter = PlantAdapter(dataPlant)
            binding.recycleViewPlant.adapter = adapter

        }
    }

    fun goBack() {
        findNavController().popBackStack()
    }



}