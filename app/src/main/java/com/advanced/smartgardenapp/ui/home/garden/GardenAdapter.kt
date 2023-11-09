package com.advanced.smartgardenapp.ui.home.garden

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.advanced.smartgardenapp.R
import com.advanced.smartgardenapp.data.model.Garden
import com.advanced.smartgardenapp.databinding.ItemGardenBinding
import com.advanced.smartgardenapp.utils.Constants

/***
 * Created by HoangRyan aka LilDua on 11/8/2023.
 */
class GardenAdapter(
    private val gardens: List<Garden>,
    private val navController: NavController
) : RecyclerView.Adapter<GardenAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemGardenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(garden: Garden) {
            binding.garden = garden
            binding.imageGarden.setImageResource(garden.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGardenBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val garden = gardens[position]
        holder.bind(garden)
        holder.itemView.setOnClickListener {
            // Handle the item click here and navigate to the new fragment with contact details
            val bundle = Bundle()
            bundle.putString(Constants.KEY_GARDEN_ID, garden.id)

            navController.navigate(
                R.id.action_homeFragment_to_gardenDetailFragment,
                bundle
            )
        }
    }

    override fun getItemCount(): Int {
        return gardens.size
    }

}