package com.java.coroutinesample.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.java.coroutinesample.R
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
        var imgUrl: String = ""
        if (beautyAndSpa.get(position).getServiceName() != null) {
            holder.mText.text = beautyAndSpa.get(position).getServiceName()
        }
//        if (beautyAndSpa.get(position).getImageUrl() != null) {
//            imgUrl = beautyAndSpa.get(position).getImageUrl().toString()
//        }
//        else{
//            imgUrl = ""
//        }

        imgUrl = "https://upload.wikimedia.org/wikipedia/commons/a/a3/June_odd-eyed-cat.jpg"
        Glide.with(activity)
            .load(imgUrl)
            .placeholder(R.drawable.loading_img)
            .error(R.drawable.error_img)
            .into(holder.icon);

    }

    override fun getItemCount(): Int {
        return beautyAndSpa.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView
        var mText: TextView

        init {
            icon = itemView.findViewById(R.id.icon)
            mText = itemView.findViewById(R.id.text)
        }
    }
}