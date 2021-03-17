package com.example.testproject

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        if(isNetworkConnected()) {
            mainViewModel.getmessages()?.observe(this, Observer {
                if (it != null)
                    mainViewModel.savedata(it)
                binding.rvMesages.adapter = it.messages?.let { it1 -> MainAdapter(it1) }
                binding.tvDoctor.text = it.consultation_request?.doctor_name
                binding.tvStatus.text = if (it.consultation_request?.consent
                                ?: false) "Online" else "Offline"
            })
        } else {
            mainViewModel.getofflinemessages()?.observe(this, Observer {
                binding.rvMesages.adapter = it.messages?.let { it1 -> MainAdapter(it1) }
                binding.tvDoctor.text = it.consultation_request?.doctor_name
                binding.tvStatus.text = /*if(it.consultation_request?.consent?:false) "Online" else */"Offline"
            })
        }


    }
    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            val activeNetwork = cm.activeNetwork
            val nc = cm.getNetworkCapabilities(activeNetwork)
            nc != null && (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
            ))
        } else {
            val activeNetwork = cm.activeNetworkInfo
            activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }
}