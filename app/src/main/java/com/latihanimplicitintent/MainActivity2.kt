package com.latihanexplicitintent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.latihanimplicitintent.R

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)  // Layout untuk Activity 2
    }
}
