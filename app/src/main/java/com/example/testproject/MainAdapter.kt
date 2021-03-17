package com.example.testproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.model.Message
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter(list: List<Message?>) :
RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: List<Message?> = list
    val format =  SimpleDateFormat("hh.mm aa", Locale.getDefault())

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var clMsg: ConstraintLayout = itemView.findViewById(R.id.clMsg)
        var clLab: ConstraintLayout = itemView.findViewById(R.id.clLab)
        var clPrescription: ConstraintLayout = itemView.findViewById(R.id.clPrescription)

        var tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
        var tvMessageTime: TextView = itemView.findViewById(R.id.tvMessageTime)

        var tvVitaminC: TextView = itemView.findViewById(R.id.tvVitaminC)

        var tvPrescdata: TextView = itemView.findViewById(R.id.tvPrescdata)
        var tvFrequency: TextView = itemView.findViewById(R.id.tvFrequency)
        var tvDays: TextView = itemView.findViewById(R.id.tvDays)



        fun bind(position: Int) {
            clMsg.visibility = View.GONE
            clLab.visibility = View.GONE
            clPrescription.visibility = View.GONE
            val recyclerViewModel = list[position]
            if(recyclerViewModel?.payload?.text!=null) {
                clMsg.visibility = View.VISIBLE
                tvMessage.text = recyclerViewModel.payload?.text
                val cal = Calendar.getInstance()
                cal.timeInMillis = recyclerViewModel.timestamp
                tvMessageTime.text = format.format(cal.time)
            } else if(recyclerViewModel?.payload?.recommended_tests!=null) {
                clLab.visibility = View.VISIBLE
                tvVitaminC.text = recyclerViewModel?.payload?.recommended_tests?.map { it.name }?.joinToString(" \n")
            } else if(recyclerViewModel?.payload?.medications!=null) {
                clPrescription.visibility = View.VISIBLE
                tvFrequency.text = recyclerViewModel?.payload?.medications?.get(0)?.frequency
                tvDays.text = recyclerViewModel?.payload?.medications?.get(0)?.number_of_days.toString() +" "+recyclerViewModel.payload?.medications?.get(0)?.duration_unit
                tvPrescdata.text = recyclerViewModel?.payload?.medications?.get(0)?.name +","+recyclerViewModel.payload?.medications?.get(0)?.dose_quantity
            }

        }
    }

    private inner class View2ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
        var tvMessageTime: TextView = itemView.findViewById(R.id.tvMsgTime)
        var clMsg: ConstraintLayout = itemView.findViewById(R.id.clMsg)
        var clImage: ConstraintLayout = itemView.findViewById(R.id.clImage)

        var tvImage: TextView = itemView.findViewById(R.id.tvImage)
        var tvImageTime: TextView = itemView.findViewById(R.id.tvImageTime)
        var imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(position: Int) {
            clMsg.visibility = View.GONE
            clImage.visibility = View.GONE
            val recyclerViewModel = list[position]
            if(recyclerViewModel?.payload?.text!=null) {
                clMsg.visibility = View.VISIBLE
                tvMessage.text = recyclerViewModel?.payload?.text
                val cal = Calendar.getInstance()
                cal.timeInMillis = recyclerViewModel?.timestamp
                tvMessageTime.text = format.format(cal.time)
            } else if(recyclerViewModel?.payload?.file_name!=null) {
                clImage.visibility = View.VISIBLE
                val cal = Calendar.getInstance()
                cal.timeInMillis = recyclerViewModel?.timestamp
                tvImageTime.text = format.format(cal.time)
                if(recyclerViewModel.payload?.file_name?.contains("pdf") == true) {
                    tvImage.text = "You have shared a document."
                    imageView.setBackgroundResource(R.drawable.pdf)
                } else {
                    tvImage.text = "You have shared an image."
                    imageView.setBackgroundResource(R.drawable.jpg)
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return View1ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.doctormsg_cell, parent, false)
            )
        }
        return View2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.mymsg_cell, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position]?.user?.type.equals("doctor")) {
            (holder as View1ViewHolder).bind(position)
        } else {
            (holder as View2ViewHolder).bind(position)
        }
    }
    override fun getItemViewType(position: Int): Int {
        if(list[position]?.user?.type.equals("doctor",true))
        return 1
        else
            return 2
    }
}