package com.example.kasirapp.data.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirapp.data.entity.Meja
import com.example.kasirapp.data.viewmodel.TableViewModel
import com.example.kasirapp.databinding.RvCardTableBinding
import com.example.kasirapp.meja.UpdateMeja

class TableAdapter(private val context: Context, private val tables : ArrayList<Meja>, private val tableViewModel: TableViewModel): RecyclerView.Adapter<TableAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: RvCardTableBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meja: Meja){
            binding.cardTableTvTitle.text = "Meja " + meja.number
            binding.cardTableIvDelete.setOnClickListener {
                tableViewModel.deleteTable(meja)
            }
        }
        fun onClick(context: Context, meja: Meja){
            binding.cardTableIvUpdate.setOnClickListener {
                val intent = Intent(context, UpdateMeja::class.java)
                intent.putExtra("id", meja.id)
                intent.putExtra("number", meja.number)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCardTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tables[position])
        holder.onClick(context, tables[position])
    }

    override fun getItemCount(): Int {
        return tables.size
    }

    fun updateTable(newList: List<Meja>) {
        tables.clear()
        tables.addAll(newList)
        notifyDataSetChanged()
    }

}