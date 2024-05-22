package com.example.myapplication
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var serviceCostEditText: EditText
    private lateinit var optionTwentyPercent: RadioButton
    private lateinit var optionEighteenPercent: RadioButton
    private lateinit var optionFifteenPercent: RadioButton
    private lateinit var onOffSwitch: Switch
    private lateinit var calculateButton: Button
    private lateinit var tipResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        serviceCostEditText = findViewById(R.id.serviceCostEditText)
        optionTwentyPercent = findViewById(R.id.optionTwentyPercent)
        optionEighteenPercent = findViewById(R.id.optionEighteenPercent)
        optionFifteenPercent = findViewById(R.id.optionFifteenPercent)
        onOffSwitch = findViewById(R.id.onOffSwitch)
        calculateButton = findViewById(R.id.calculateButton)
        tipResult = findViewById(R.id.tipResult)
        calculateButton.setOnClickListener {
            calculateTip()
        }
    }
    private fun calculateTip() {
        // Check if the service cost is empty
        val serviceCostStr = serviceCostEditText.text.toString()
        if (serviceCostStr.isEmpty()) {
            Toast.makeText(this, "Please enter the service cost.", Toast.LENGTH_SHORT).show()
            return
        }
        val serviceCost = serviceCostStr.toDouble()
        val tipPercentage = when {
            optionTwentyPercent.isChecked -> 0.20 // 20%
            optionEighteenPercent.isChecked -> 0.18 // 18%
            optionFifteenPercent.isChecked -> 0.15 // 15%
            else -> {
                Toast.makeText(this, "Please select a tip percentage.", Toast.LENGTH_SHORT).show()
                return
            }
        }
        var tipAmount = serviceCost * tipPercentage
        if (onOffSwitch.isChecked) {
            tipAmount = Math.ceil(tipAmount)
        }
        val decimalFormat = DecimalFormat("#.##")
        val formattedTipAmount = decimalFormat.format(tipAmount)
        tipResult.text = "Tip Amount: $$formattedTipAmount"
    }
}
