package com.example.doss_house.tickets
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.textclassifier.TextClassificationSessionId
import androidx.appcompat.app.AppCompatActivity
import com.example.doss_house.MainActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.doss_house.databinding.TicketSearchActivityBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ChildEventListener
import android.util.Log
import com.google.firebase.database.getValue
import java.text.SimpleDateFormat
import java.util.Locale



class SerachTickets : AppCompatActivity() {
    lateinit var binding: TicketSearchActivityBinding
    private val adapter = TicketAdapter()
    private lateinit var databaseRef: DatabaseReference
    lateinit var routeList: ArrayList<Ticket>
    private val calendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TicketSearchActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("My log", "Search activity was opened.")

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

        binding.searchButton.setOnClickListener {
            Log.d("My log", "Searchbutton was clicked.")

            databaseRef = FirebaseDatabase.getInstance().getReference("Routes")
            Log.d("My log", "Connected to db routes.")
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

                    /*val arrivalPoint : String = snapshot.child("arrivalPoint").getValue<String>()!!

                    val arrivalTime : String = snapshot.child("arrivalTime").getValue<String>()!!

                    val date : String = snapshot.child("date").getValue<String>()!!

                    val depPoint : String = snapshot.child("depPoint").getValue<String>()!!

                    val depTime : String = snapshot.child("depTime").getValue<String>()!!

                    val id : String = snapshot.child("id").getValue<String>()!!

                    val price : String = snapshot.child("price").getValue<Double>().toString()!!
                    val tickets : Int = snapshot.child("tickets").getValue<Int>()!!
                    */
                    Log.d("My log", "$i ${route.depPoint}-${route.arrivalPoint} " +
                            "${route.depTime}-${route.arrivalTime} ${route.price}" +
                            "${route.tickets} ${route.date}")
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
