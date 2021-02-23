package com.java.coroutinesample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.java.coroutinesample.model.DataModel

class MyAdpter(activity: FragmentActivity?, beautyAndSpa: List<DataModel>) :
    RecyclerView.Adapter<MyAdpter.MyViewHolder>() {
    lateinit var activity: FragmentActivity
    var beautyAndSpa: List<DataModel>

    init {
        if (activity != null) {
            this.activity = activity
        }
        this.beautyAndSpa = beautyAndSpa
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.my_child_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (beautyAndSpa.get(position).getServiceName() != null) {
            holder.mText.text = beautyAndSpa.get(position).getServiceName()
        }
    }

    override fun getItemCount(): Int {
        return beautyAndSpa.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon : ImageView
        var mText : TextView

        init {
            icon = itemView.findViewById(R.id.icon)
            mText = itemView.findViewById(R.id.text)
        }
    }
}