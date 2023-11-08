package com.example.doss_house.user
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doss_house.R
import com.example.doss_house.databinding.TripTemplateBinding
import com.example.doss_house.tickets.Ticket

class OrderAdapter : RecyclerView.Adapter <OrderAdapter.OHolder>(){
    val currentOrders = ArrayList<Order>()

    class OHolder(item : View) : RecyclerView.ViewHolder(item){
        val binding = TripTemplateBinding.bind(item)

        fun bind (order : Order) = with(binding){
            orderIDText.text = order.id
            profileDirectionText.text = order.direction
            profileDateText.text = order.date
            profileTimeText.text = order.time
            profileAmountText.text = order.amount
            profilePriceText.text = order.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.OHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.trip_template, parent, false)
        return OHolder(view)
    }

    override fun onBindViewHolder(holder: OrderAdapter.OHolder, position: Int) {
        holder.bind(currentOrders[position])
    }

    override fun getItemCount(): Int {
        return currentOrders.size
    }
    fun addOrder (order : Order){
        currentOrders.add(order)
        notifyDataSetChanged()
    }
    fun clear(){
        this.currentOrders.clear()
        notifyDataSetChanged()
    }
}