package com.example.kasirapp.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kasirapp.data.entity.Beverage
import com.example.kasirapp.data.entity.Food
import com.example.kasirapp.data.viewmodel.MenuViewModel
import com.example.kasirapp.databinding.ActivityUpdateMenuBinding

class UpdateMenu : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateMenuBinding
    private lateinit var menuViewModel: MenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.updateMenuIbBack.setOnClickListener {
            startActivity(Intent(this, DataMenu::class.java ))
            finish()
        }

        val id = intent.getIntExtra("id", 0)
        val variety = intent.getStringExtra("variety")
        val menuName = binding.updateMenuEtName
        val description = binding.updateMenuEtDesc
        val harga = binding.updateMenuEtHarga


        menuName.setText(intent.getStringExtra("menuName"))
        description.setText(intent.getStringExtra("desc"))
        harga.setText(intent.getStringExtra("price"))

        menuViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MenuViewModel::class.java]

        binding.updateMenuBtnSave.setOnClickListener {
            val newMenuName = menuName.text.toString()
            val newDescription = description.text.toString()
            val newHarga = harga.text.toString()

            when{

                newMenuName.isEmpty() -> alert()
                newDescription.isEmpty() -> alert()
                newHarga.isEmpty() -> alert()

                else -> {
                    val price = harga.text.toString().toInt()

                    if (variety == "Makanan"){

                        val updatedFood = Food(name = newMenuName, description = newDescription, price = price, image = 0)
                        updatedFood.id = id
                        menuViewModel.updateFood(updatedFood)
                        startActivity(Intent(this, DataMenu::class.java))
                        finish()
                    }

                    else {

                        val updatedBeverage = Beverage(name = newMenuName, description = newDescription, price = price, image = 0)
                        updatedBeverage.id = id
                        menuViewModel.updateBeverage(updatedBeverage)
                        startActivity(Intent(this, DataMenu::class.java))
                        finish()
                    }
                }
            }
        }
    }

    private fun alert(){
        Toast.makeText(this, "Enter The Right Value", Toast.LENGTH_SHORT).show()
    }
}