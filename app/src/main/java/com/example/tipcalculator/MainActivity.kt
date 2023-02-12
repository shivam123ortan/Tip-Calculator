package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import com.google.android.material.color.DynamicColors
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculate.setOnClickListener{calculateTip()}
    }

    private fun calculateTip() {
        val cost = binding.cost.text.toString().toDouble()
        if (cost == null) {
            binding.answer.text = ""
            return
        }
        val tipPercentage = when(binding.radioGroup.checkedRadioButtonId){
            R.id.amazing -> 0.20
            R.id.good -> 0.18
            else -> 0.15
        }
        var tipAmount = cost * tipPercentage
        val roundUp = binding.roundUpSwitch.isChecked

        if (roundUp){
            tipAmount = kotlin.math.ceil(tipAmount)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tipAmount)
        binding.answer.text = getString(R.string.tip_amount, formattedTip)

    }
}