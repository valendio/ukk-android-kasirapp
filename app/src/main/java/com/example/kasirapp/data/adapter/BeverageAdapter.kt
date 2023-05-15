package com.example.kasirapp.data.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirapp.R
import com.example.kasirapp.menu.UpdateMenu
import com.example.kasirapp.data.entity.Beverage
import com.example.kasirapp.data.viewmodel.MenuViewModel
import com.example.kasirapp.databinding.RvCardMinumanBinding

class BeverageAdapter(private val context: Context,private val beverages : ArrayList<Beverage>, private val beverageViewModel: MenuViewModel): RecyclerView.Adapter<BeverageAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding : RvCardMinumanBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(beverage: Beverage){
            binding.cardMinumanTvTitle.text = beverage.name
            binding.cardMinumanTvDesc.text = beverage.description
            binding.cardMinumanTvHarga.text = beverage.price.toString()
            when(beverage.image){
                1 -> binding.cardMinumanIvImage.setImageResource(R.drawable.esteh)

                2 -> binding.cardMinumanIvImage.setImageResource(R.drawable.eskelapa)

                3 -> binding.cardMinumanIvImage.setImageResource(R.drawable.eslemon)

                else -> binding.cardMinumanIvImage.setImageResource(R.drawable.ic_image)
            }
            binding.cardMinumanIvDelete.setOnClickListener {
                beverageViewModel.deleteBeverage(beverage)
            }

        }

        fun onClick(context: Context, beverage: Beverage){
            binding.cardMinumanIvUpdate.setOnClickListener {
                val intent = Intent(context, UpdateMenu::class.java)
                intent.putExtra("id", beverage.id)
                intent.putExtra("menuName", beverage.name)
                intent.putExtra("variety", "Minuman")
                intent.putExtra("desc", beverage.description)
                intent.putExtra("price", beverage.price.toString())
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCardMinumanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(beverages[position])
        holder.onClick(context = context, beverages[position])
    }

    override fun getItemCount(): Int {
        return beverages.size
    }

    fun updateBeverage(newList: List<Beverage>){
        beverages.clear()
        beverages.addAll(newList)
        notifyDataSetChanged()
    }
}