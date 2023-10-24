package com.example.doss_house

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.doss_house.databinding.TicketTemplateBinding

class TicketAdapter : RecyclerView.Adapter <TicketAdapter.THolder>(){
    val ticketList = ArrayList<Ticket>()
    class THolder(item: View) : RecyclerView.ViewHolder(item){
        val tElementBinding = TicketTemplateBinding.bind(item)
        fun bind(ticket: Ticket) = with(tElementBinding){
            TODO("Создать функцию для заполнения активити. Данные брать из базы")
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