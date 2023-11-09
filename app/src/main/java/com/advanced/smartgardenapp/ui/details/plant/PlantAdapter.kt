package com.advanced.smartgardenapp.ui.details.plant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advanced.smartgardenapp.data.model.Plant
import com.advanced.smartgardenapp.databinding.ItemPlantBinding

/***
 * Created by HoangRyan aka LilDua on 11/9/2023.
 */
class PlantAdapter(
    private val plants: List<Plant>
) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemPlantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: Plant) {
            binding.plant = plant
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlantBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = plants[position]
        holder.bind(plant)
    }

    override fun getItemCount(): Int {
        return plants.size
    }

}