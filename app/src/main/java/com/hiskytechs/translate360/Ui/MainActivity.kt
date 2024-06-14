package com.hiskytechs.translate360.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hiskytechs.translate360.R
import com.hiskytechs.translate360.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     binding=DataBindingUtil.setContentView(this@MainActivity,R.layout.activity_main)
    }
}