package com.example.doss_house.tickets
//import android.view.textclassifier.TextClassificationSessionId
//import com.example.doss_house.MainActivity
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doss_house.MainActivity
import com.example.doss_house.databinding.TicketSearchActivityBinding
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt


class SerachTickets : AppCompatActivity(), TicketAdapter.Listener{

    lateinit var binding: TicketSearchActivityBinding
    private val adapter = TicketAdapter(this)
    private lateinit var databaseRef: DatabaseReference
    /*lateinit var routeList: ArrayList<Ticket>*/
    private val calendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TicketSearchActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("My log", "Before init")
        init()

        binding.dateText.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this, { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    // Create a new Calendar instance to hold the selected date

                    val selectedDate = Calendar.getInstance()
                    // Set the selected date using the values received from the DatePicker dialog
                    selectedDate.set(year, monthOfYear, dayOfMonth)
                    // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                    val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                    // Format the selected date into a string
                    val formattedDate = dateFormat.format(selectedDate.time)
                    // Update the TextView to display the selected date with the "Selected Date: " prefix
                    binding.dateText.text = "$formattedDate"

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

    }


    private fun init(){
        binding.apply {
            recyclerViewTickets.layoutManager = LinearLayoutManager(this@SerachTickets)
            recyclerViewTickets.adapter = adapter
            binding.searchButton.setOnClickListener {
                adapter.clear()
                databaseRef = FirebaseDatabase.getInstance().getReference("Routes")
                val path = binding.dateText.text.toString() + "/" +
                        binding.depPointText.text.toString().lowercase() +
                        binding.arrivalPointText.text.toString().lowercase()

                Log.d("My log", path)

                databaseRef.child(path).addChildEventListener(object : ChildEventListener{
                    var i : Int = 0
                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?){

                        val route = Ticket(snapshot.child("arrivalPoint").getValue<String>()!!,
                            snapshot.child("arrivalTime").getValue<String>()!!,
                            snapshot.child("date").getValue<String>()!!,
                            snapshot.child("depPoint").getValue<String>()!!,
                            snapshot.child("depTime").getValue<String>()!!,
                            snapshot.child("id").getValue<String>()!!,
                            snapshot.child("price").getValue<Double>()!!,
                            snapshot.child("tickets").getValue<Int>()!!)

                       // Log.d("My log", "$i ${route.depPoint}-${route.arrivalPoint} " +
                         //       "${route.depTime}-${route.arrivalTime} ${route.price}" +
                           //     "${route.tickets} ${route.date}")

                        adapter.addTicket(route)
                        i++
                    }
                    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                        Log.d("My log", "Child changed.")
                        val arrivalPoint : String
                        arrivalPoint = snapshot.child("arrivalPoint").getValue<String>()!!
                        //route = snapshot.getValue<Ticket>()!!
                        Log.d("My log", arrivalPoint.toString()!!)
                    }
                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?){
                        Log.d("My log", "Error occured.")

                    }
                    override fun onChildRemoved(snapshot: DataSnapshot) {

                        Log.d("My log", "chiled removed.")

                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.d("My log", "Error occured.")
                    }
                })
            }
        }
    }

    override fun onClick(ticket: Ticket) {
        var totalPrice =  (binding.passangersAmountText.text.toString().toInt() *
                ticket.price).toFloat()
        val intent = Intent(this, BookTicketActivity::class.java).apply {
            putExtra("depPoint", ticket.depPoint)
            putExtra("depTime", ticket.depTime)
            putExtra("arvPoint", ticket.arrivalPoint)
            putExtra("arvTime", ticket.arrivalTime)
            putExtra("date", ticket.date)
            putExtra("amount", binding.passangersAmountText.text.toString())
            putExtra("price", totalPrice.toString())
        }
       startActivity(intent)
    }
}
