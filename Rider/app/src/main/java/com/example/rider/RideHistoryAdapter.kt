package com.example.rider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RideHistoryAdapter(val rideList: ArrayList<RideHistory>, val itemClickListener: (View, Int, Int) -> Unit): RecyclerView.Adapter<RideHistoryAdapter.ViewHolder>() {
    fun <T: RecyclerView.ViewHolder> T.onClick(event: (view:View, position:Int, type:Int) -> Unit): T{
        itemView.setOnClickListener {
            event.invoke(it, adapterPosition, itemViewType)
        }
        return this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.ride_history, parent, false)
        val vh = ViewHolder(v)
        vh.onClick(itemClickListener)
        return vh
    }

    override fun getItemCount(): Int {
        return rideList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ride: RideHistory = rideList[position]

        holder.textViewMonth?.text = ride.month
        holder.textViewDay?.text = ride.day
        holder.textViewTime?.text = ride.time
        holder.textViewFrom?.text = ride.from
        holder.textViewTo?.text = ride.to
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewMonth = itemView.findViewById<TextView>(R.id.month)
        val textViewDay = itemView.findViewById<TextView>(R.id.day)
        val textViewTime = itemView.findViewById<TextView>(R.id.time)
        val textViewFrom = itemView.findViewById<TextView>(R.id.from)
        val textViewTo = itemView.findViewById<TextView>(R.id.to)
    }
}