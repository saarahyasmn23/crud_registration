package com.example.regiscrud

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class ReadRegistration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_registration)

        val intent = intent
        val name = intent.getStringExtra("name")
        val medicineList = intent.getStringExtra("medicineList")
        val birthdate = intent.getStringExtra("birthdate")
        val weight = intent.getIntExtra("weight", 0)
        val height = intent.getIntExtra("height", 0)
        val gerdValue = intent.getIntExtra("gerdValue", 0)

        // Retrieve selected radio button values
        val selectedGender = intent.getStringExtra("selectedGenderId")
        val selectedFrequency = intent.getStringExtra("selectedFrequencyId")

        val fullName = findViewById<TextView>(R.id.fullname)
        fullName.text = name

        val tvBirthdate = findViewById<TextView>(R.id.birthdate)
        tvBirthdate.text = birthdate

        val tvGender = findViewById<TextView>(R.id.gender)
        tvGender.text = selectedGender

        val tvWeight = findViewById<TextView>(R.id.weigth)
        tvWeight.text = weight.toString()

        val tvHeight = findViewById<TextView>(R.id.height)
        tvHeight.text = height.toString()

        val tvGerdValue = findViewById<TextView>(R.id.period)
        tvGerdValue.text = gerdValue.toString()

        val tvFrequency = findViewById<TextView>(R.id.symptomps)
        tvFrequency.text = selectedFrequency

        val medicineLists = findViewById<TextView>(R.id.medlist)
        medicineLists.text = medicineList

        val btnLogOut: Button = findViewById(R.id.delete)

        btnLogOut.setOnClickListener {
            val message: String? = "Are you sure you want to delete?!"
            showCustomDialogBox(message)
        }
    }

    private fun showCustomDialogBox(message: String?) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.alert)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvMessage: TextView = dialog.findViewById(R.id.tvMessage)
        val btnYes: Button = dialog.findViewById(R.id.btnYes)
        val btnNo: Button = dialog.findViewById(R.id.btnNo)

        tvMessage.text = message

        btnYes.setOnClickListener {
            Toast.makeText(this, "click on Yes", Toast.LENGTH_LONG).show()
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}