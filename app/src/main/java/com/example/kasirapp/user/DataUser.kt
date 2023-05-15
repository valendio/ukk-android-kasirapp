package com.example.kasirapp.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirapp.MainActivity
import com.example.kasirapp.data.adapter.UserAdapter
import com.example.kasirapp.data.entity.User
import com.example.kasirapp.data.viewmodel.UserViewModel
import com.example.kasirapp.databinding.ActivityDataUserBinding

class DataUser : AppCompatActivity() {

    private lateinit var binding : ActivityDataUserBinding
    private lateinit var userViewModel: UserViewModel
    private var users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        userViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UserViewModel::class.java]

        binding.dataUserIbBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.dataUserFabAdd.setOnClickListener {
            startActivity(Intent(this, AddUser::class.java))
            finish()
        }

        initRvUser()
    }

    private fun initRvUser(){
        val userAdapter = UserAdapter(this, users, userViewModel)

        val rvUser = binding.dataUserRvUser
        rvUser.setHasFixedSize(true)
        rvUser.layoutManager = LinearLayoutManager(this)
        rvUser.adapter = userAdapter

        userViewModel.getuser.observe(this) { list ->
            list?.let {
                users = it as ArrayList<User>
                userAdapter.updateUser(users)
            }
        }
    }
}