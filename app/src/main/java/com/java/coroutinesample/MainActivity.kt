package com.java.coroutinesample


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.java.coroutinesample.adapter.MyAdpter
import com.java.coroutinesample.model.DataModel
import com.java.coroutinesample.model.DataResponse
import com.java.coroutinesample.network.MyApi
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
   private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.recyclerView)

        lifecycleScope.launch {
            fetchData()
        }

//        attachFragmentWithActivity()
        
    }

    private fun attachFragmentWithActivity() {
        if (supportFragmentManager.findFragmentById(R.id.content) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content, MyFragment())
                .commit()
        }
    }

    private suspend fun fetchData() {
        val response   = MyApi().getDataValues()
        updateUI(response.body())
    }

    private fun updateUI(body: DataResponse?) {
        val linearLayoutManager = LinearLayoutManager(this)
        mRecyclerView.setLayoutManager(linearLayoutManager)
        val adapter = MyAdpter(this, body?.getBeautyAndSpa() as List<DataModel>)
        mRecyclerView.setAdapter(adapter)
    }

}