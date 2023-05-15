package com.example.kasirapp.meja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kasirapp.data.entity.Meja
import com.example.kasirapp.data.viewmodel.TableViewModel
import com.example.kasirapp.databinding.ActivityAddMejaBinding

class AddMeja : AppCompatActivity() {

    private lateinit var binding : ActivityAddMejaBinding
    private lateinit var tableViewModel: TableViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMejaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        tableViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TableViewModel::class.java]

        binding.addTableIbBack.setOnClickListener {
            startActivity(Intent(this, DataMeja::class.java ))
            finish()
        }

        binding.addTableBtnSave.setOnClickListener {
            val number = binding.addTableEtNumber.text.toString()

            when{
                number.isEmpty() -> alert()

                else -> {
                    val numberTable = number.toInt()

                    tableViewModel.insertTable(Meja(number = numberTable))
                    startActivity(Intent(this, DataMeja::class.java))
                    finish()
                }
            }

        }
    }
    private fun alert(){
        Toast.makeText(this, "Enter The Right Value", Toast.LENGTH_SHORT).show()
    }
}