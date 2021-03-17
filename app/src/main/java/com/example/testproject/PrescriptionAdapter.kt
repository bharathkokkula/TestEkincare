package com.example.testproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.model.Medications
import com.example.testproject.model.Message
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PrescriptionAdapter(list: List<Medications?>) :
RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<Medications?> = list
    private inner class PresriptionViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        var tvPrescdata: TextView = itemView.findViewById(R.id.tvPrescdata)
        var tvFrequency: TextView = itemView.findViewById(R.id.tvFrequency)
        var tvDays: TextView = itemView.findViewById(R.id.tvDays)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            tvFrequency.text = recyclerViewModel?.frequency
            tvDays.text = recyclerViewModel?.number_of_days.toString() +" "+recyclerViewModel?.duration_unit
            tvPrescdata.text = recyclerViewModel?.name +","+recyclerViewModel?.dose_quantity
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PresriptionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.prescription_subcell, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PresriptionViewHolder).bind(position)
    }
}