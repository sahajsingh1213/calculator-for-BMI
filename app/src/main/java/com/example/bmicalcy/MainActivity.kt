package com.example.bmicalcy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.inputWeight)
        val heightText = findViewById<EditText>(R.id.inputheight)
        val cal = findViewById<Button>(R.id.cal_result)
        val resultBox = findViewById<CardView>(R.id.cv_result)
        resultBox.visibility = View.INVISIBLE

        cal.setOnClickListener {
            var weight = weightText.text.toString()
            var height = heightText.text.toString()
            if (validateUserInpute(weight, height)){
               resultBox.visibility = View.VISIBLE
            val bmiCalculation = weight.toFloat() / ((height.toFloat() * 0.3048).pow(2))
            val bmiResultFormat = String.format("%.2f", bmiCalculation).toFloat()
                displayResult(bmiResultFormat)
        }
        }
    }

    fun validateUserInpute(weight:String?,Height:String?) =  when {
        weight.isNullOrEmpty()->{
            Toast.makeText(this,
                "Weight is empty",
                Toast.LENGTH_SHORT).show()
            false
        }

        Height.isNullOrEmpty()->{
            Toast.makeText(this,
                "Height is empty",
                Toast.LENGTH_SHORT).show()
             false
        }
        else ->{ true}

    }


    fun displayResult(bmi:Float){
        val additionalInfo = findViewById<TextView>(R.id.extra_result)
        val advisedRange = findViewById<TextView>(R.id.cv_range)
        val resultc = findViewById<TextView>(R.id.result_bmr)
        resultc.text = bmi.toString()
        advisedRange.text = "(Normal range is 18.5 to 24.9)"

        var color = 0
        var moreadvise = ""

        when{
            bmi<18.5->{
                moreadvise = "You are underWeight"
                color = R.color.UnderWeight
            }

            bmi in 18.5..24.9->{
                moreadvise = "You are Healthy"
                color = R.color.Healthy
            }

            bmi in 25.0 .. 29.9->{
                moreadvise = "You are OverWeight"
                color = R.color.OverWeight
            }

            bmi>30.0-> {
                moreadvise = "You are obese"
                color = R.color.Obese
            }

        }
        additionalInfo.setTextColor(ContextCompat.getColor(this,color))
        additionalInfo.text = moreadvise

    }
}