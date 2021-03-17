package com.example.testproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.model.Message
import java.text.SimpleDateFormat
import java.util.*

class MainAdapter(list: List<Message?>, maincontext: Context?) :
RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: List<Message?> = list
    var context = maincontext
    val format =  SimpleDateFormat("hh.mm aa", Locale.getDefault())

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
        var tvMessageTime: TextView = itemView.findViewById(R.id.tvMessageTime)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            tvMessage.text = recyclerViewModel?.payload?.text
            val cal = Calendar.getInstance()
            cal.timeInMillis = recyclerViewModel?.timestamp?:0L
            tvMessageTime.text = format.format(cal.time)
        }
    }
    private inner class PresriptionViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        var rvList: RecyclerView = itemView.findViewById(R.id.rvList)

        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            rvList?.layoutManager = LinearLayoutManager(context)
            rvList?.adapter = recyclerViewModel?.payload?.medications?.let { PrescriptionAdapter(it) }
        }
    }
    private inner class LabViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        var tvVitaminC: TextView = itemView.findViewById(R.id.tvVitaminC)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            tvVitaminC.text = recyclerViewModel?.payload?.recommended_tests?.map { it.name }?.joinToString(" \n")
        }
    }

    private inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
        var tvMessageTime: TextView = itemView.findViewById(R.id.tvMsgTime)

        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            tvMessage.text = recyclerViewModel?.payload?.text
            val cal = Calendar.getInstance()
            cal.timeInMillis = recyclerViewModel?.timestamp?:0L
            tvMessageTime.text = format.format(cal.time)
        }
    }

    private inner class ImageViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        var tvImage: TextView = itemView.findViewById(R.id.tvImage)
        var tvImageTime: TextView = itemView.findViewById(R.id.tvImageTime)
        var imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            val cal = Calendar.getInstance()
            cal.timeInMillis = recyclerViewModel?.timestamp?:0L
            tvImageTime.text = format.format(cal.time)
            if(recyclerViewModel?.payload?.file_name?.contains("pdf") == true) {
                tvImage.text = "You have shared a document."
                imageView.setBackgroundResource(R.drawable.pdf)
            } else {
                tvImage.text = "You have shared an image."
                imageView.setBackgroundResource(R.drawable.jpg)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return View1ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.doctormsg_cell, parent, false))
        } else if(viewType == 2) {
            return LabViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lab_cell, parent, false))
        } else if(viewType == 3) {
            return PresriptionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.prescription_cell, parent, false))
        } else if(viewType == 4){
            return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.mymsg_cell, parent, false))
        } else {
            return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_cell, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position]?.user?.type.equals("doctor")) {
            if(list[position]?.payload?.medications!=null) {
                (holder as PresriptionViewHolder).bind(position)
            } else if(list[position]?.payload?.recommended_tests!=null) {
                (holder as LabViewHolder).bind(position)
            } else if(list[position]?.payload?.text!=null) {
                (holder as View1ViewHolder).bind(position)
            }
        } else {
            if(list[position]?.payload?.text!=null)
            (holder as MyViewHolder).bind(position)
            else {
                (holder as ImageViewHolder).bind(position)
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        if(list[position]?.user?.type.equals("doctor",true)) {
            if(list[position]?.payload?.medications!=null) {
                return 3
            } else if(list[position]?.payload?.recommended_tests!=null) {
                return 2
            } else if(list[position]?.payload?.text!=null) {
                return 1
            }
        } else {
            if(list[position]?.payload?.text!=null) {
                return 4
            }  else if(list[position]?.payload?.file_name!=null) {
                return 5
            }
        }
        return 1
    }
}