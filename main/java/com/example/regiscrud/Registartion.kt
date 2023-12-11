package com.example.regiscrud

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Registartion : AppCompatActivity(), OnItemClicked {
    private lateinit var tRarelyTextView: TextView
    private lateinit var tSeldomTextView: TextView
    private lateinit var tOftenTextView: TextView
    private lateinit var weightValueTextView: TextView
    private lateinit var heightValueTextView: TextView
    private lateinit var gerdValueTextView: TextView
    private lateinit var tvDatePicker: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        //this is for Name Input
        val editTextName = findViewById<EditText>(R.id.editTextFullName)
        editTextName.hint = "Enter your Name"

        editTextName.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                // When the EditText is focused, clear the hint text
                editTextName.hint = ""
            } else {
                // When the EditText loses focus, set the hint text back if it's empty
                if (editTextName.text.isEmpty()) {
                    editTextName.hint = "Enter your Name"
                }
            }
        }

        //This is for madicine input
        val editTextMedicine = findViewById<EditText>(R.id.editTextText3)
        editTextMedicine.hint = "Enter your Medicine List"

        editTextName.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                // When the EditText is focused, clear the hint text
                editTextMedicine.hint = ""
            } else {
                // When the EditText loses focus, set the hint text back if it's empty
                if (editTextMedicine.text.isEmpty()) {
                    editTextMedicine.hint = "Enter your Medicine List"
                }
            }
        }

        tvDatePicker = findViewById(R.id.editTextBirthdate)

        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar)
        }

        weightValueTextView = findViewById(R.id.textViewWeightValue)

        val buttonWeightIncrement = findViewById<ImageButton>(R.id.buttonWeightIncrement)
        val buttonWeightDecrement = findViewById<ImageButton>(R.id.buttonWeightDecrement)

        var weightValue = 50

        buttonWeightIncrement.setOnClickListener {
            // Increment the weight value
            weightValue++
            // Update the TextView with the new value
            weightValueTextView.text = weightValue.toString()
        }
        buttonWeightDecrement.setOnClickListener {
            // Decrement the weight value, but make sure it doesn't go below a certain value (e.g., 0)
            if (weightValue > 0) {
                weightValue--
                // Update the TextView with the new value
                weightValueTextView.text = weightValue.toString()
            }
        }

        heightValueTextView = findViewById(R.id.textViewHeightValue)

        val buttonHeightIncrement = findViewById<ImageButton>(R.id.buttonHeightIncrement)
        val buttonHeightDecrement = findViewById<ImageButton>(R.id.buttonHeightDecrement)

        var heightValue = 170

        buttonHeightIncrement.setOnClickListener {
            // Increment the weight value
            heightValue++
            // Update the TextView with the new value
            heightValueTextView.text = heightValue.toString()
        }
        buttonHeightDecrement.setOnClickListener {
            // Decrement the weight value, but make sure it doesn't go below a certain value (e.g., 0)
            if (heightValue > 0) {
                heightValue--
                // Update the TextView with the new value
                heightValueTextView.text = heightValue.toString()
            }
        }

        gerdValueTextView = findViewById(R.id.textViewGerdValue)

        val buttonGerdIncrement = findViewById<ImageButton>(R.id.buttonGerdIncrement)
        val buttonGerdDecrement = findViewById<ImageButton>(R.id.buttonGerdDecrement)

        var gerdValue = 5

        buttonGerdIncrement.setOnClickListener {
            // Increment the weight value
            gerdValue++
            // Update the TextView with the new value
            gerdValueTextView.text = gerdValue.toString()
        }
        buttonGerdDecrement.setOnClickListener {
            // Decrement the weight value, but make sure it doesn't go below a certain value (e.g., 0)
            if (gerdValue > 0) {
                gerdValue--
                // Update the TextView with the new value
                gerdValueTextView.text = gerdValue.toString()
            }
        }

        val genderRadioButtonGroup = findViewById<RadioGroup>(R.id.gender)
        val gerdRadioButtonGroup = findViewById<RadioGroup>(R.id.rgGerd)
        tRarelyTextView = findViewById(R.id.tRarely)
        tSeldomTextView = findViewById(R.id.tSeldom)
        tOftenTextView = findViewById(R.id.tOften)

        genderRadioButtonGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbMale -> {
                    // Set the color of the selected radio button to pink
                    findViewById<RadioButton>(R.id.rbMale).buttonTintList =
                        ColorStateList.valueOf(Color.parseColor("#FF597B"))

                    // Set the color of the unselected radio button to gray
                    findViewById<RadioButton>(R.id.rbFemale).buttonTintList =
                        ColorStateList.valueOf(Color.parseColor("#A1A8B0"))

                }
                R.id.rbFemale -> {
                    // Set the color of the selected radio button to pink
                    findViewById<RadioButton>(R.id.rbFemale).buttonTintList =
                        ColorStateList.valueOf(Color.parseColor("#FF597B"))

                    // Set the color of the unselected radio button to gray
                    findViewById<RadioButton>(R.id.rbMale).buttonTintList =
                        ColorStateList.valueOf(Color.parseColor("#A1A8B0"))
                }
            }
        }

        gerdRadioButtonGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rRarely -> {
                    // Set the color of the selected radio button to pink
                    findViewById<RadioButton>(R.id.rRarely).buttonTintList =
                        ColorStateList.valueOf(Color.parseColor("#FF597B"))

                    // Set the color of the unselected radio button to gray
                    findViewById<RadioButton>(R.id.rSeldom).buttonTintList =
                        ColorStateList.valueOf(Color.parseColor("#A1A8B0"))
                    findViewById<RadioButton>(R.id.rOften).buttonTintList =
                        ColorStateList.valueOf(Color.parseColor("#A1A8B0"))

                    tRarelyTextView.setTextColor(Color.parseColor("#FF597B"))
                    tSeldomTextView.setTextColor(Color.parseColor("#101623"))
                    tOftenTextView.setTextColor(Color.parseColor("#101623"))
                }
                R.id.rSeldom -> {
                    // Set the color of the selected radio button to pink
                    findViewById<RadioButton>(R.id.rSeldom).buttonTintList =
                        ColorStateList.valueOf(Color.parseColor("#FF597B"))

                    // Set the color of the unselected radio button to gray
                    findViewById<RadioButton>(R.id.rRarely).buttonTintList =
                        ColorStateList.valueOf(Color.parseColor("#A1A8B0"))
                    findViewById<RadioButton>(R.id.rOften).buttonTintList =
                        ColorStateList.valueOf(Color.parseColor("#A1A8B0"))

                    tRarelyTextView.setTextColor(Color.parseColor("#101623"))
                    tSeldomTextView.setTextColor(Color.parseColor("#FF597B"))
                    tOftenTextView.setTextColor(Color.parseColor("#101623"))
                }
                R.id.rOften -> {
                    // Set the color of the selected radio button to pink
                    findViewById<RadioButton>(R.id.rOften).buttonTintList =
                        ColorStateList.valueOf(Color.parseColor("#FF597B"))

                    // Set the color of the unselected radio button to gray
                    findViewById<RadioButton>(R.id.rSeldom).buttonTintList =
                        ColorStateList.valueOf(Color.parseColor("#A1A8B0"))
                    findViewById<RadioButton>(R.id.rRarely).buttonTintList =
                        ColorStateList.valueOf(Color.parseColor("#A1A8B0"))

                    tRarelyTextView.setTextColor(Color.parseColor("#101623"))
                    tSeldomTextView.setTextColor(Color.parseColor("#101623"))
                    tOftenTextView.setTextColor(Color.parseColor("#FF597B"))
                }
            }
        }

        tvDatePicker.setOnClickListener {
            DatePickerDialog(this, datePicker,myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val buttonClick = findViewById<Button>(R.id.btnsubmit)
        buttonClick.setOnClickListener {
            val searchIntent = Intent(this@Registartion, ReadRegistration::class.java)
            val intent = Intent(this, ReadRegistration::class.java)

            val fullName = editTextName.text.toString()
            val birthdate = tvDatePicker.text.toString()
            val medicineList = editTextMedicine.text.toString()
            val weightValue = weightValue
            val heightValue = heightValue
            val gerdValue = gerdValue

            intent.putExtra("name", fullName)
            intent.putExtra("medicineList", medicineList)
            intent.putExtra("birthdate", birthdate)
            intent.putExtra("weight", weightValue)
            intent.putExtra("height", heightValue)
            intent.putExtra("gerdValue", gerdValue)

            val radioGroupGender = findViewById<RadioGroup>(R.id.gender)
            val radioGroupFrequency = findViewById<RadioGroup>(R.id.rgGerd)
//            val btnSubmit = findViewById<Button>(R.id.btnsubmit)

            val selectedGenderId = radioGroupGender.checkedRadioButtonId
            val selectedFrequencyId = radioGroupFrequency.checkedRadioButtonId

            if (selectedGenderId != -1) {
                val radioButtonGender = findViewById<RadioButton>(selectedGenderId)
                searchIntent.putExtra("selectedGender", radioButtonGender.text.toString())
            }

            if (selectedFrequencyId != -1) {
                val radioButtonFrequency = findViewById<RadioButton>(selectedFrequencyId)
                searchIntent.putExtra("selectedFrequency", radioButtonFrequency.text.toString())
            }

            startActivity(intent)
        }

        findViewId()
        btnSave.apply{
            setOnClickListener{
                setDataToDatabase(getUserData())
            }
        }

        getDataFromDatabase()
    }

    private fun updateLable(myCalendar: Calendar){
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        tvDatePicker.setText(sdf.format(myCalendar.time))
    }

//    fun addListenerOnButton(){
//        val radioGroupGender = findViewById<RadioGroup>(R.id.gender)
//        val radioGroupFrequency = findViewById<RadioGroup>(R.id.rgGerd)
//        val btnSubmit = findViewById<Button>(R.id.btnsubmit)
//
//        btnSubmit.setOnClickListener {
//            val selectedGenderId = radioGroupGender.checkedRadioButtonId
//            val selectedFrequencyId = radioGroupFrequency.checkedRadioButtonId
//
//            val radioButtonGender = findViewById<RadioButton>(selectedGenderId)
//            val radioButtonFrequency = findViewById<RadioButton>(selectedFrequencyId)
//
//            Toast.makeText(this@Registartion, radioButtonGender.text, Toast.LENGTH_SHORT).show()
//            Toast.makeText(this@Registartion, radioButtonFrequency.text, Toast.LENGTH_SHORT).show()
//        }
//    }

    private fun findById() {

    }

    private fun setDataToDatabase(user: User){
        if (user.name.isNotEmpty() && user.nim.isNotEmpty()){
            btnSave.isEnabled = false

            db.getReference("user").child(user.id).setValue(user).addOnSuccesListener {
                etName.setText("")
                etNim.setText("")
                btnSave.isEnabled = true

                Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                btnSave.isEnabled = true

                Toast.makeText(this,"Data gagal disimpan", Toast.LENGTH_SHORT).show()
            }
        }
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(etName.windowToken, 0)
        imm.hideSoftInputFromWindow(etNim.windowToken, 0)
    }

    private fun getDataFromDatabase() {
        val progressBar = findViewById<ProgressBar>(R.id.pbCircular)
        progressBar.visibility = View.VISIBLE

        db.getReference("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progressBar.visibility = View.GONE
                listKey.clear()
                listUser.clear()

                snapshot.children.map {
                    it.key?.let { userId ->
                        listKey.add(userId)
                    }
                }

                listKey.map {
                    listUser.add (
                        user(
                            id = snapshot.child(it).child("id").value.toString(),
                            name = snapshot.child(it).child("name").value.toString(),
                            nim = snapshot.child(it).child("nim").value.toString()
                        )
                    )
                }

                adapterUser = UserAdapter(listUser.sortedBy {it.name },
                    this@MainActivity)
                initRecycleView()
            }

            override  fun onCancelled(error: DatabaseError) {
                progressBar.visibility = View.GONE
                Log.e("DB ERROR", error.message)
            }
        })
    }

    private fun initRecycleView() {
        rvUser = findViewById(R.id.rvUser)
        rvUser.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,
                    false)
            adapter = adapterUser
        }
    }
}