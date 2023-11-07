package com.example.doss_house.log_in

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.doss_house.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.doss_house.tickets.SerachTickets
import com.example.doss_house.databinding.RegistrationActivityBinding



class SignUp : AppCompatActivity() {
    lateinit var binding: RegistrationActivityBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistrationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUpButton.setOnClickListener {
            val email = binding.signUpEmailText.text.toString()
            val pass = binding.signUpPassword.text.toString()
            val passConfirm = binding.signUpPassword2.text.toString()
            //TODO("add try catch if fields are not empty and passwords comparison")
            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(this, SerachTickets ::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}