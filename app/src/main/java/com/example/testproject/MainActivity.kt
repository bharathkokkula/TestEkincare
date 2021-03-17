package com.example.testproject

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvMesages.layoutManager = LinearLayoutManager(this)
        mainViewModel.appdb(this)

        if(!basecheckConnection()) {
            mainViewModel.getmessages()?.observe(this, Observer {
                if(it!=null )
                    mainViewModel.savedata(it)
                binding.rvMesages.adapter = it.messages?.let { it1 -> MainAdapter(it1) }
                binding.tvDoctor.text = it.consultation_request?.doctor_name
                binding.tvStatus.text = if(it.consultation_request?.consent?:false) "Online" else "Offline"
            })
        } else {
            mainViewModel.getofflinemessages()?.observe(this, Observer {
                binding.rvMesages.adapter = it.messages?.let { it1 -> MainAdapter(it1) }
                binding.tvDoctor.text = it.consultation_request?.doctor_name
                binding.tvStatus.text = /*if(it.consultation_request?.consent?:false) "Online" else */"Offline"
            })
        }


    }
    fun basecheckConnection():Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
       return cm.isActiveNetworkMetered
    }
}