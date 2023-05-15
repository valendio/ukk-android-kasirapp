package com.example.kasirapp.data.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirapp.R
import com.example.kasirapp.data.entity.User
import com.example.kasirapp.data.viewmodel.UserViewModel
import com.example.kasirapp.databinding.RvCardUserBinding
import com.example.kasirapp.user.UpdateUser


class UserAdapter(private val context: Context, private val  users: ArrayList<User>, private val userViewModel: UserViewModel): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: RvCardUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.cardUserTvTitle.text = user.name
            binding.cardUserTvDesc.text = user.job
            when (user.image) {
                1 -> binding.cardUserIvImage.setImageResource(R.drawable.user_satu)

                2 -> binding.cardUserIvImage.setImageResource(R.drawable.user_dua)

                3 -> binding.cardUserIvImage.setImageResource(R.drawable.user_tiga)

                else -> binding.cardUserIvImage.setImageResource(R.drawable.ic_image)
            }
            binding.cardUserIvDelete.setOnClickListener {
                userViewModel.deleteUser(user)
            }
        }

        fun onClick(context: Context, user: User){
            binding.cardUserIvUpdate.setOnClickListener {
                val intent = Intent(context, UpdateUser::class.java)
                intent.putExtra("id", user.id)
                intent.putExtra("name", user.name)
                intent.putExtra("username", user.username)
                intent.putExtra("password", user.password)
                intent.putExtra("job", user.job)
                context.startActivity(intent)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCardUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
        holder.onClick(context = context, users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun updateUser(newList: List<User>) {
        users.clear()
        users.addAll(newList)
        notifyDataSetChanged()
    }
}
