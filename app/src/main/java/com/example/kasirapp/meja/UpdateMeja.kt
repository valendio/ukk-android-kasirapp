package com.example.kasirapp.meja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kasirapp.data.entity.Meja
import com.example.kasirapp.data.viewmodel.TableViewModel
import com.example.kasirapp.databinding.ActivityUpdateMejaBinding

class UpdateMeja : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateMejaBinding
    private lateinit var tableViewModel: TableViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateMejaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.updateTableIbBack.setOnClickListener {
            startActivity(Intent(this, DataMeja::class.java))
            finish()
        }

        val id = intent.getIntExtra("id", 0)
        val number = intent.getIntExtra("number", 0)

        binding.updateTableEtNumber.setText(number.toString())

        tableViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TableViewModel::class.java]

        binding.updateTableBtnSave.setOnClickListener {
            val newNumber = binding.updateTableEtNumber.text.toString()

            when{
                newNumber.isEmpty() -> alert()

                else -> {
                    val numberTable = newNumber.toInt()

                    val updatedTable = Meja(number = numberTable)
                    updatedTable.id = id
                    tableViewModel.updateTable(updatedTable)
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