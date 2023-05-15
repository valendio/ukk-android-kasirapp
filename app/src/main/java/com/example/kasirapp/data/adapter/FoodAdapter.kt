package com.example.kasirapp.data.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirapp.R
import com.example.kasirapp.menu.UpdateMenu
import com.example.kasirapp.data.entity.Food
import com.example.kasirapp.data.viewmodel.MenuViewModel
import com.example.kasirapp.databinding.RvCardMakananBinding

class FoodAdapter(private val context: Context,private val foods : ArrayList<Food>, private val foodViewModel: MenuViewModel): RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding : RvCardMakananBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food){
            binding.cardMakananTvTitle.text = food.name
            binding.cardMakananTvDesc.text = food.description
            binding.cardMakananTvHarga.text = food.price.toString()
            when(food.image){
                1 -> binding.cardMakananIvImage.setImageResource(R.drawable.nasgor)

                2 -> binding.cardMakananIvImage.setImageResource(R.drawable.gado_gado)

                3 -> binding.cardMakananIvImage.setImageResource(R.drawable.pecel)

                else -> binding.cardMakananIvImage.setImageResource(R.drawable.ic_image)
            }
            binding.cardMakananIvDelete.setOnClickListener {
                foodViewModel.deleteFood(food)
            }
        }
        fun onClick(context: Context, food: Food){
            binding.cardMakananIvUpdate.setOnClickListener {
                val intent = Intent(context, UpdateMenu::class.java)
                intent.putExtra("id", food.id)
                intent.putExtra("menuName", food.name)
                intent.putExtra("variety", "Makanan")
                intent.putExtra("desc", food.description)
                intent.putExtra("price", food.price.toString())
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCardMakananBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(foods[position])
        holder.onClick(context = context, foods[position])

    }

    override fun getItemCount(): Int {
        return foods.size
    }

    fun updateFood(newList: List<Food>){
        foods.clear()
        foods.addAll(newList)
        notifyDataSetChanged()
    }
}
