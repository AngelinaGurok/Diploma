package com.example.doss_house.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.doss_house.databinding.ProfileInfoActivityBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import com.example.doss_house.tickets.SerachTickets

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ProfileInfoActivityBinding
    private val adapter = OrderAdapter()
    private lateinit var databaseRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileInfoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        binding.Backbutton.setOnClickListener {
            val intent = Intent(this, SerachTickets ::class.java)
            startActivity(intent)
        }
    }
    private fun init(){
        binding.apply {
            recyclerViewOrders.layoutManager = LinearLayoutManager(this@ProfileActivity)
            recyclerViewOrders.adapter = adapter
            databaseRef = FirebaseDatabase.getInstance().getReference("Orders")
            val userId = FirebaseAuth.getInstance().uid.toString()
            databaseRef.child(userId).addChildEventListener(object : ChildEventListener{
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val order = Order(snapshot.child("userID").getValue<String>()!!,
                        snapshot.child("id").getValue<String>()!!,
                        snapshot.child("direction").getValue<String>()!!,
                        snapshot.child("date").getValue<String>()!!,
                        snapshot.child("time").getValue<String>()!!,
                        snapshot.child("amount").getValue<String>()!!,
                        snapshot.child("price").getValue<String>()!!)

                    adapter.addOrder(order)
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    Log.d("My log", "Child changed.")

                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                    Log.d("My log", "Child removed.")

                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                    Log.d("My log", "child moved.")
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("My log", "Error occured.")
                }
            })
        }
    }
}