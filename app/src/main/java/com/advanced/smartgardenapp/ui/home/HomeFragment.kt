package com.advanced.smartgardenapp.ui.home

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.advanced.smartgardenapp.data.model.Datasource
import com.advanced.smartgardenapp.databinding.FragmentHomeBinding
import com.advanced.smartgardenapp.ui.home.garden.GardenAdapter
import java.util.Calendar
import java.util.Locale

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


            //get data for humidity and temperature
            viewModel.humidity.observe(viewLifecycleOwner) {
                binding.textActualHumidity.text = "$it%"
            }
            viewModel.temperature.observe(viewLifecycleOwner) {
                binding.textActualTemperature.text = "$it°c"
            }
            viewModel.setHumidity.observe(viewLifecycleOwner) {
                binding.editHumidity.setText(it)
            }
            viewModel.setTemperature.observe(viewLifecycleOwner) {
                binding.editTemperature.setText(it)
            }

            //set time
            viewModel.setTime1.observe(viewLifecycleOwner) {
                binding.textSetTime1.text = it
            }
            viewModel.setTime2.observe(viewLifecycleOwner) {
                binding.textSetTime2.text = it
            }
            viewModel.setTime3.observe(viewLifecycleOwner) {
                binding.textSetTime3.text = it
            }


            viewModel.fetchActualHumidityValue()
            viewModel.fetchActualTemperatureValue()
            viewModel.fetchSetHumidityValue()
            viewModel.fetchSetTemperatureValue()
            viewModel.fetchSetTime1Value()
            viewModel.fetchSetTime2Value()
            viewModel.fetchSetTime3Value()

            //set actions
            setFanStatus()
            setWaterPumpStatus()
            checkAutoFan()
            checkEnableCheckBox()
            setTimeCheckBoxListener()
        }
    }

    private fun checkEnableCheckBox() {
        //check box 1
        viewModel.fetchCheckBox1State()
        viewModel.checkBox1.observe(viewLifecycleOwner) {
            binding.checkboxWaterPump1.isChecked = it?.toIntOrNull() == 1
        }

        //check box 2
        viewModel.fetchCheckBox2State()
        viewModel.checkBox2.observe(viewLifecycleOwner) {
            binding.checkboxWaterPump2.isChecked = it?.toIntOrNull() == 1
        }

        //check box 3
        viewModel.fetchCheckBox3State()
        viewModel.checkBox3.observe(viewLifecycleOwner) {
            binding.checkboxWaterPump3.isChecked = it?.toIntOrNull() == 1
        }
    }

    private fun setTimeCheckBoxListener() {
        //check box 1
        binding.checkboxWaterPump1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.setCheckBox1Value("1")
            }else {
                viewModel.setCheckBox1Value("0")
            }
        }

        //check box 2
        binding.checkboxWaterPump2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.setCheckBox2Value("1")
            }else {
                viewModel.setCheckBox2Value("0")
            }
        }

        //check box 3
        binding.checkboxWaterPump3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.setCheckBox3Value("1")
            }else {
                viewModel.setCheckBox3Value("0")
            }
        }
    }

    fun setTemperature() {
        viewModel.setTemperatureValue(binding.editTemperature.text.toString())
    }

    fun setHumidity() {
        viewModel.setHumidityValue(binding.editHumidity.text.toString())
    }

    private fun checkAutoFan() {
        //get data state from database
        viewModel.fetchCheckAutoFanStatusValue()
        viewModel.checkAutoFanStatus.observe(viewLifecycleOwner) {
            binding.checkboxAutoFan.isChecked = it?.toIntOrNull() == 1
        }

        //set user action from app
        binding.checkboxAutoFan.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                viewModel.setCheckAutoFanStatusValue("1")
                binding.checkboxAutoFan.isChecked = true

                //handle logic for get temperature
                viewModel.temperature.observe(viewLifecycleOwner) { temperature ->
                    viewModel.setTemperature.observe(viewLifecycleOwner) { setTemperature ->
                        val intValue = setTemperature?.replace("\"", "")?.toIntOrNull()
                        if (temperature != null) {
                            if (temperature.toIntOrNull() ?: 0 > intValue!!) {
                                //if temperature > 20 -> fan status is on
                                binding.textFanStatus.text = "On"
                                binding.switchFan.isChecked = true
                                viewModel.setFanStatusValue("1")
                            }else {
                                //else temperature <= 20 -> fan status is off
                                binding.textFanStatus.text = "Off"
                                binding.switchFan.isChecked = false
                                viewModel.setFanStatusValue("0")
                            }
                        }
                    }
                }

            }else {
                viewModel.setCheckAutoFanStatusValue("0")
                binding.checkboxAutoFan.isChecked = false
            }
        }
    }

    private fun setWaterPumpStatus() {
        //get data from database
        viewModel.fetchWaterPumpStatusValue()
        viewModel.waterPumpStatus.observe(viewLifecycleOwner) {
            if(it?.toIntOrNull() == 1) {
                binding.textWaterPumpStatus.text = "On"
                binding.switchWaterPump.isChecked = true
            }else {
                binding.textWaterPumpStatus.text = "Off"
                binding.switchWaterPump.isChecked = false
            }
        }

        //set user action from app
        binding.switchWaterPump.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                viewModel.setWaterPumpStatusValue("1")
                binding.textWaterPumpStatus.text = "On"
            }else {
                viewModel.setWaterPumpStatusValue("0")
                binding.textWaterPumpStatus.text = "Off"
            }
        }

    }

    private fun setFanStatus() {
        //get data from database
        viewModel.fetchFanStatusValue()
        viewModel.fanStatus.observe(viewLifecycleOwner) {
            if(it?.toIntOrNull() == 1) {
                binding.textFanStatus.text = "On"
                binding.switchFan.isChecked = true
            }else {
                binding.textFanStatus.text = "Off"
                binding.switchFan.isChecked = false
            }
        }

        //set user action from app
        binding.switchFan.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                viewModel.setFanStatusValue("1")
                binding.textFanStatus.text = "On"
            }else {
                viewModel.setFanStatusValue("0")
                binding.textFanStatus.text = "Off"
            }
        }
    }

    fun setTime1() { //set time 1
        showTimePickerDialog(binding.textSetTime1)
    }
    fun setTime2() { //set time 2
        showTimePickerDialog(binding.textSetTime2)
    }
    fun setTime3() {   //set time 3
        showTimePickerDialog(binding.textSetTime3)
    }

    private fun showTimePickerDialog(textView: TextView) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, selectedHour, selectedMinute ->
                val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)
                textView.text = formattedTime
                when(textView) {
                    binding.textSetTime1 -> viewModel.setTime1Value(formattedTime)
                    binding.textSetTime2 -> viewModel.setTime2Value(formattedTime)
                    binding.textSetTime3 -> viewModel.setTime3Value(formattedTime)
                }
            },
            hour,
            minute,
            true
        )

        timePickerDialog.show()
    }

}