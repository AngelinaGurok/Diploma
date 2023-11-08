package com.example.doss_house.tickets

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doss_house.R
import com.example.doss_house.databinding.TicketTemplateBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference



class TicketAdapter(val listener : Listener) : RecyclerView.Adapter <TicketAdapter.THolder>(){
    val ticketList = ArrayList<Ticket>()
    class THolder(item: View) : RecyclerView.ViewHolder(item){
        val binding = TicketTemplateBinding.bind(item)
        fun bind(ticket: Ticket, itemListener: Listener) = with(binding){

            routeDirectionText.text = "${ticket.depPoint}-${ticket.arrivalPoint}"
            ticketsAmountText.text = ticket.tickets.toString()
            depTimeText.text = ticket.depTime
            arrivalTimeText.text = ticket.arrivalTime
            priceText.text = ticket.price.toString()
            itemView.setOnClickListener {
                Log.d("My log", "Item was clicked")
                itemListener.onClick(ticket)

            }
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
        holder.bind(ticketList[position], listener)
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

    fun clear (){
        this.ticketList.clear()
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(ticket: Ticket)
    }
}