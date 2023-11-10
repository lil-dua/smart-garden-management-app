package com.advanced.smartgardenapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.advanced.smartgardenapp.utils.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    // Actual humidity -----------------------------------------------------------------------------
    private val humidityReference: DatabaseReference = database.getReference(Constants.KEY_ACTUAL_HUMIDITY)
    val humidity: MutableLiveData<String?> = MutableLiveData()
    fun fetchActualHumidityValue() {
        GlobalScope.launch(Dispatchers.IO) {
            humidityReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val actualHumidity = snapshot.getValue(Long::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            humidity.value = actualHumidity.toString()
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    // Actual temperature -----------------------------------------------------------------------------
    private val temperatureReference: DatabaseReference = database.getReference(Constants.KEY_ACTUAL_TEMPERATURE)
    val temperature: MutableLiveData<String?> = MutableLiveData()
    fun fetchActualTemperatureValue() {
        GlobalScope.launch(Dispatchers.IO) {
            temperatureReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val actualTemperature = snapshot.getValue(Long::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            temperature.value = actualTemperature.toString()
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }
}