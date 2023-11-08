package com.example.doss_house.tickets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.doss_house.databinding.AvailableTicketsActivityBinding
import com.example.doss_house.databinding.BookTicketBinding

class BookTicketActivity : AppCompatActivity() {
    lateinit var binding : BookTicketBinding
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

        }

        binding.discardButton.setOnClickListener {
            val intent = Intent(this, SerachTickets::class.java)
            startActivity(intent)
        }

    }
}