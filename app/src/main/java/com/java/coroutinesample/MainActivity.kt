package com.java.coroutinesample

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)


        if (supportFragmentManager.findFragmentById(R.id.content) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content, MyFragment())
                .commit()
        }


    }


}