package com.example.gastos

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gastos.databinding.ItemGastoBinding
import com.example.gastos.room.GastoModel

class GastoAdapter(var datalist: List<GastoModel>) : RecyclerView.Adapter<GastoAdapter.GastoHolder>() {

  inner class GastoHolder(val binding: ItemGastoBinding) : RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GastoHolder {
    val binding = ItemGastoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return GastoHolder(binding)
  }

  override fun onBindViewHolder(holder: GastoHolder, position: Int) {
    var item = datalist[position]
    holder.binding.tvAmount.text = item.amount.toString()
    holder.binding.tvDate.text = item.date.toString()
    holder.binding.tvName.text = item.name
    holder.binding.tvState.text = item.state.toString()
    holder.binding.ivEdit.setOnClickListener {
      var intent = Intent(holder.binding.root.context, ActivityAdd::class.java)
      MainActivity.isEdit = true
      MainActivity.gastoSelect = item
      holder.binding.root.context.startActivity(intent)
    }

  }

  override fun getItemCount(): Int {
    return datalist.size
  }

}