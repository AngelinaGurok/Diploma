package com.example.doss_house.tickets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doss_house.R
import com.example.doss_house.databinding.TicketTemplateBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference



class TicketAdapter : RecyclerView.Adapter <TicketAdapter.THolder>(){
    val ticketList = ArrayList<Ticket>()
    class THolder(item: View) : RecyclerView.ViewHolder(item){
        val binding = TicketTemplateBinding.bind(item)
        fun bind(ticket: Ticket) = with(binding){
           // TODO("Создать функцию для заполнения активити. Данные брать из базы")
            routeDirectionText.text = "${ticket.depPoint}-${ticket.arrivalPoint}"
            ticketsAmountText.text = ticket.tickets.toString()
            depTimeText.text = ticket.depTime
            arrivalTimeText.text = ticket.arrivalTime
            priceText.text = ticket.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): THolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ticket_template, parent, false)
        return THolder(view)
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }

    override fun onBindViewHolder(holder: THolder, position: Int) {
        holder.bind(ticketList[position])
    }

    fun addTicket(ticket: Ticket){
        ticketList.add(ticket)
        notifyDataSetChanged()
    }

    fun addAll (list: List<Ticket>){
       ticketList.clear()
       ticketList.addAll(list)
       notifyDataSetChanged()
    }
}