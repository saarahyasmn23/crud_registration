package com.example.regiscrud

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserRegistrationAdapter(private val list: List<UserRegistration>, val itemClicked: OnItemClicked) :
        RecyclerView.Adapter<UserRegistrationAdapter.UserAdapterViewHolder>(){
            inner class UserRegistrationAdapterViewHolder(private val view: View): RecyclerView.viewHolder(view){
                fun bind(data: UserRegistration) {
                    view.apply {
                        val fullName = findViewById<TextView>(R.id.fullname)
                        val tvBirthdate = findViewById<TextView>(R.id.birthdate)
                        val tvGender = findViewById<TextView>(R.id.gender)
                        val tvWeight = findViewById<TextView>(R.id.weigth)
                        val tvHeight = findViewById<TextView>(R.id.height)
                        val tvGerdValue = findViewById<TextView>(R.id.period)
                        val tvFrequency = findViewById<TextView>(R.id.symptomps)
                        val medicineLists = findViewById<TextView>(R.id.medlist)
                        val btnEdit = findViewById<Button>(R.id.edit)
                        val btnLogOut: Button = findViewById(R.id.delete)

                        fullName.text = data.fullName
                        tvBirthdate.text = data.birthdate.toString()
                        tvGender.text = data.gender
                        tvWeight.text = data.weight.toString()
                        tvHeight.text = data.height.toString()
                        tvGerdValue.text = data.gerdPeriod
                        tvFrequency.text = data.gerdFrequency
                        medicineLists.text = data.medicineList

                        btnEdit.setOnClickListener{
                            itemClicked.editClicked(data)
                        }

                        btnLogOut.setOnClickListener{
                            itemClicked.deleteClicked(data)
                        }
                    }
                }
            }
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): UserRegistrationAdapter.UserRegistrationAdapterViewHolder{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_read_registration, parent, false)
                return UserRegistrationAdapterViewHolder(view)
            }

    override fun onBindViewHolder(
        holder: UserRegistrationAdapter.UserAdapterViewHolder,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
        }

interface onItemClicked {
    fun editClicked(data: UserRegistration)

    fun deleteClicked(data: UserRegistration)
}