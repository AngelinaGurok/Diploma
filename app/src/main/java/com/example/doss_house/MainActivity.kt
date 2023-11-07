package com.example.doss_house

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.content.Intent
import android.icu.util.Calendar
//import android.util.Log
//import android.view.View
//import android.widget.Button
//import android.widget.DatePicker
//import android.widget.ImageView
//import android.widget.TextView
import java.util.*
import java.text.SimpleDateFormat
import com.example.doss_house.databinding.AvailableTicketsActivityBinding
/*
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
*/


class MainActivity : AppCompatActivity() {
    lateinit var mainBinding : AvailableTicketsActivityBinding
    private val calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = AvailableTicketsActivityBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

       /* mainBinding.dateArrive.setOnClickListener {

            val datePickerDialog = DatePickerDialog(
                this, { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    // Create a new Calendar instance to hold the selected date

                    val selectedDate = Calendar.getInstance()
                    // Set the selected date using the values received from the DatePicker dialog
                    selectedDate.set(year, monthOfYear, dayOfMonth)
                    // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    // Format the selected date into a string
                    val formattedDate = dateFormat.format(selectedDate.time)
                    // Update the TextView to display the selected date with the "Selected Date: " prefix
                    mainBinding.dateArrive.text = "$formattedDate"

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }*/


        /*mainBinding.departure.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()

            val datePickerDialog = DatePickerDialog(
                this, { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    // Create a new Calendar instance to hold the selected date
                    val selectedDate = Calendar.getInstance()
                    // Set the selected date using the values received from the DatePicker dialog
                    selectedDate.set(year, monthOfYear, dayOfMonth)
                    // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    // Format the selected date into a string
                    val formattedDate = dateFormat.format(selectedDate.time)
                    // Update the TextView to display the selected date with the "Selected Date: " prefix
                    mainBinding.departure.text = "$formattedDate"

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)

            )
            datePickerDialog.show()
        }*/
    }
}