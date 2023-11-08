package com.example.doss_house.tickets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.doss_house.databinding.BookTicketBinding
import com.example.doss_house.user.Order
import com.example.doss_house.user.ProfileActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BookTicketActivity : AppCompatActivity() {
    lateinit var binding : BookTicketBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BookTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var depPoint = intent.getStringExtra("depPoint")
        var depTime = intent.getStringExtra("depTime")
        var arvPoint = intent.getStringExtra("arvPoint")
        var arvTime = intent.getStringExtra("arvTime")
        var date = intent.getStringExtra("date")
        var amount = intent.getStringExtra("amount")
        var price = intent.getStringExtra("price")

        binding.apply {
            bookDepPointText.text = depPoint
            bookDepTimeText.text = depTime
            bookArvPointText.text = arvPoint
            bookArvTimeText.text = arvTime
            bookDateText.text = date
            bookAmountText.text = amount
            bookTotalPriceText.text = price
        }

        binding.bookConfirmButton.setOnClickListener {
            database = FirebaseDatabase.getInstance().getReference("Orders")
            binding.apply {
                val user = FirebaseAuth.getInstance().uid.toString()

                val direction = bookDepPointText.text.toString() + "-" +
                        bookArvPointText.text.toString()
                val date = bookDateText.text.toString()
                val time = bookDepTimeText.text.toString() + "-" +
                        bookArvTimeText.text.toString()
                val amount = bookAmountText.text.toString()
                val price = bookTotalPriceText.text.toString()
                val id = database.push().key.toString()

                val order = Order(user, id, direction, date, time, amount, price)
                database.child(order.userID).child(order.id).setValue(order)
                database = FirebaseDatabase.getInstance().getReference("Routes")
                val path = date + "/" + depPoint + arvPoint + binding.bookDepTimeText.text.toString().lowercase() +
                        binding.bookArvTimeText.text.toString().lowercase()

            }
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.discardButton.setOnClickListener {
            val intent = Intent(this, SerachTickets::class.java)
            startActivity(intent)
        }

    }
}