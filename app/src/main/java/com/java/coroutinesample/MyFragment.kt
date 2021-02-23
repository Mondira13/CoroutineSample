package com.java.coroutinesample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.java.coroutinesample.model.DataModel
import com.java.coroutinesample.model.DataResponse
import com.java.coroutinesample.network.MyApi
import kotlinx.coroutines.launch

class MyFragment : Fragment() {
    lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val view = inflater.inflate(R.layout.fragment_blank, container, false)
        mRecyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            fetchData()
        }

    }

    private suspend fun fetchData() {
        val response   = MyApi().getDataValues()
        updateUI(response.body())
    }

    private fun updateUI(body: DataResponse?) {
        val linearLayoutManager = LinearLayoutManager(activity)
        mRecyclerView.setLayoutManager(linearLayoutManager)
        val adapter = MyAdpter(activity, body?.getBeautyAndSpa() as List<DataModel>)
        mRecyclerView.setAdapter(adapter)
    }


}