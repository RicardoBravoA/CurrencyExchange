package com.rba.currency.exchange

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rba.currency.exchange.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
//        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}