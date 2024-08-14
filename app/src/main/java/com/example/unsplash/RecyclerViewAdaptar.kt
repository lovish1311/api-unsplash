package com.example.unsplash

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.databinding.StudentDetailsItemBinding

class recyclerViewAdaptar( var list:ArrayList<studentDetails>,var context:Context):
    RecyclerView.Adapter<recyclerViewAdaptar.myViewHolder>() {
    inner class myViewHolder(var binding:StudentDetailsItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var binding = StudentDetailsItemBinding.inflate(LayoutInflater.from(context))
        return myViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.binding.tvRollNumber.text=list.get(position).rollNo
        holder.binding.tvPhoneNumber.text=list.get(position).phoneNumber
    }
}