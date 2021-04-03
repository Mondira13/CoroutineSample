package com.java.coroutinesample


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
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
   private lateinit var mReload: Button
   private lateinit var mProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.recyclerView)
        mReload = findViewById(R.id.reload)
        mProgressBar = findViewById(R.id.progressBar)


        if(!isInternetAvailable(this)){
            mReload.visibility = View.VISIBLE
            Toast.makeText(this@MainActivity,"Network disconnected.!!",Toast.LENGTH_LONG).show()
        }else{
            loadData()
        }

        mReload.setOnClickListener {
            loadData()
        }

//        attachFragmentWithActivity()
        
    }

    private fun loadData(){
        mReload.visibility = View.GONE
        mProgressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            fetchData()
        }
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
        if(body?.getBeautyAndSpa() != null) {
            mProgressBar.visibility = View.GONE
            val linearLayoutManager = LinearLayoutManager(this)
            mRecyclerView.setLayoutManager(linearLayoutManager)
            val adapter = MyAdpter(this, body.getBeautyAndSpa() as List<DataModel>)
            mRecyclerView.setAdapter(adapter)
        }
    }

    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }
}


