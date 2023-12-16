package com.advanced.smartgardenapp.ui.home

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
//MVVM - Model View ViewModel

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
    val temperature: MutableLiveData<Long?> = MutableLiveData()
    fun fetchActualTemperatureValue() {
        GlobalScope.launch(Dispatchers.IO) {
            temperatureReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val actualTemperature = snapshot.getValue(Long::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            temperature.value = actualTemperature
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }


    //Set value -----------------------------------------------------------------------------------------
    private val dataReference: DatabaseReference = database.getReference(Constants.KEY_FIELD_GET_DATA)

    private val fanStatusReference = dataReference.child(Constants.KEY_FAN)
    val fanStatus: MutableLiveData<String?> = MutableLiveData()
    fun fetchFanStatusValue() {
        GlobalScope.launch(Dispatchers.IO) {
            fanStatusReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val fanStatusValue = snapshot.getValue(String::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            fanStatus.value = fanStatusValue
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }
    fun setFanStatusValue(newValue: String) {
        //sử dụng Coroutine trong Kotlin để tránh block UI Main Thread, chạy trong luồng IO
        GlobalScope.launch(Dispatchers.IO) {
            fanStatusReference.setValue(newValue)
        }
    }

    private val checkAutoFanStatusReference = dataReference.child(Constants.KEY_CHECK_AUTO)
    val checkAutoFanStatus: MutableLiveData<String?> = MutableLiveData()
    fun fetchCheckAutoFanStatusValue() {
        GlobalScope.launch(Dispatchers.IO) {
            checkAutoFanStatusReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val checkAutoFanStatusValue = snapshot.getValue(String::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            checkAutoFanStatus.value = checkAutoFanStatusValue
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    fun setCheckAutoFanStatusValue(newValue: String) {
        GlobalScope.launch(Dispatchers.IO) {
            checkAutoFanStatusReference.setValue(newValue)
        }
    }


    private val waterPumpStatusReference = dataReference.child(Constants.KEY_PUMP)
    val waterPumpStatus: MutableLiveData<String?> = MutableLiveData()
    fun fetchWaterPumpStatusValue() {
        GlobalScope.launch(Dispatchers.IO) {
            waterPumpStatusReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val waterPumpStatusValue = snapshot.getValue(String::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            waterPumpStatus.value = waterPumpStatusValue
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    fun setWaterPumpStatusValue(newValue: String) {
        GlobalScope.launch(Dispatchers.IO) {
            waterPumpStatusReference.setValue(newValue)
        }
    }


    private val setTemperatureReference = dataReference.child(Constants.KEY_SET_TEMPERATURE)
    val setTemperature: MutableLiveData<String?> = MutableLiveData()
    fun fetchSetTemperatureValue() {
        GlobalScope.launch(Dispatchers.IO) {
            setTemperatureReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val setTemperatureValue = snapshot.getValue(String::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            setTemperature.value = setTemperatureValue
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    fun setTemperatureValue(newValue: String) {
        GlobalScope.launch(Dispatchers.IO) {
            setTemperatureReference.setValue(newValue)
        }
    }

    private val setHumidityReference = dataReference.child(Constants.KEY_SET_HUMIDITY)
    val setHumidity: MutableLiveData<String?> = MutableLiveData()

    fun fetchSetHumidityValue() {
        GlobalScope.launch(Dispatchers.IO) {
            setHumidityReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val setHumidityValue = snapshot.getValue(String::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            setHumidity.value = setHumidityValue
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }
    fun setHumidityValue(newValue: String) {
        GlobalScope.launch(Dispatchers.IO) {
            setHumidityReference.setValue(newValue)
        }
    }


    //Set time -----------------------------------------------------------------------------------
    private val setTime1Reference = dataReference.child(Constants.KEY_SET_TIME_1)
    val setTime1: MutableLiveData<String?> = MutableLiveData()

    fun fetchSetTime1Value() {
        GlobalScope.launch(Dispatchers.IO) {
            setTime1Reference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val setTime1Value = snapshot.getValue(String::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            setTime1.value = setTime1Value
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    fun setTime1Value(newValue: String) {
        GlobalScope.launch(Dispatchers.IO) {
            setTime1Reference.setValue(newValue)
        }
    }

    private val setTime2Reference = dataReference.child(Constants.KEY_SET_TIME_2)
    val setTime2: MutableLiveData<String?> = MutableLiveData()

    fun fetchSetTime2Value() {
        GlobalScope.launch(Dispatchers.IO) {
            setTime2Reference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val setTime2Value = snapshot.getValue(String::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            setTime2.value = setTime2Value
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    fun setTime2Value(newValue: String) {
        GlobalScope.launch(Dispatchers.IO) {
            setTime2Reference.setValue(newValue)
        }
    }

    private val setTime3Reference = dataReference.child(Constants.KEY_SET_TIME_3)
    val setTime3: MutableLiveData<String?> = MutableLiveData()

    fun fetchSetTime3Value() {
        GlobalScope.launch(Dispatchers.IO) {
            setTime3Reference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val setTime3Value = snapshot.getValue(String::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            setTime3.value = setTime3Value
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    fun setTime3Value(newValue: String) {
        GlobalScope.launch(Dispatchers.IO) {
            setTime3Reference.setValue(newValue)
        }
    }

    //Check box ------------------------------------------------------------------------------------
    private val checkBox1Reference = dataReference.child(Constants.KEY_CHECK_BOX_1)
    val checkBox1: MutableLiveData<String?> = MutableLiveData()

    fun fetchCheckBox1State() {
        GlobalScope.launch(Dispatchers.IO) {
            checkBox1Reference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val checkBox1Value = snapshot.getValue(String::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            checkBox1.value = checkBox1Value
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    fun setCheckBox1Value(newValue: String) {
        GlobalScope.launch(Dispatchers.IO) {
            checkBox1Reference.setValue(newValue)
        }
    }

    private val checkBox2Reference = dataReference.child(Constants.KEY_CHECK_BOX_2)
    val checkBox2: MutableLiveData<String?> = MutableLiveData()

    fun fetchCheckBox2State() {
        GlobalScope.launch(Dispatchers.IO) {
            checkBox2Reference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val checkBox2Value = snapshot.getValue(String::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            checkBox2.value = checkBox2Value
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    fun setCheckBox2Value(newValue: String) {
        GlobalScope.launch(Dispatchers.IO) {
            checkBox2Reference.setValue(newValue)
        }
    }

    private val checkBox3Reference = dataReference.child(Constants.KEY_CHECK_BOX_3)
    val checkBox3: MutableLiveData<String?> = MutableLiveData()

    fun fetchCheckBox3State() {
        GlobalScope.launch(Dispatchers.IO) {
            checkBox3Reference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val checkBox3Value = snapshot.getValue(String::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            checkBox3.value = checkBox3Value
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    fun setCheckBox3Value(newValue: String) {
        GlobalScope.launch(Dispatchers.IO) {
            checkBox3Reference.setValue(newValue)
        }
    }


    //Fetch rain sensor status
    private val rainSensorReference = database.getReference(Constants.KEY_RAIN_SENSOR)
    val rainSensor: MutableLiveData<String?> = MutableLiveData()
    fun fetchRainSensorStatus() {
        GlobalScope.launch(Dispatchers.IO) {
            rainSensorReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val rainSensorState = snapshot.getValue(String::class.java)
                        GlobalScope.launch(Dispatchers.Main) {
                            rainSensor.value = rainSensorState
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