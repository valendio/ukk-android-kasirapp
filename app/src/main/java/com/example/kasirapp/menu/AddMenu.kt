package com.example.kasirapp.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kasirapp.R
import com.example.kasirapp.data.entity.Beverage
import com.example.kasirapp.data.entity.Food
import com.example.kasirapp.data.viewmodel.MenuViewModel
import com.example.kasirapp.databinding.ActivityAddMenuBinding

class AddMenu : AppCompatActivity() {

    private lateinit var binding : ActivityAddMenuBinding
    private lateinit var menuViewModel: MenuViewModel
    private var variety = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        menuViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MenuViewModel::class.java]

        binding.addMenuIbBack.setOnClickListener {
            startActivity(Intent(this, DataMenu::class.java ))
            finish()
        }


        val spinner = binding.addMenuSpVariety

        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_menu,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                variety = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.addMenuBtnSave.setOnClickListener{
            val menuName = binding.addMenuEtName.text.toString()
            val description = binding.addMenuEtDesc.text.toString()
            val harga = binding.addMenuEtHarga.text.toString()

            when{

                menuName.isEmpty() -> alert()
                variety == "Pilih" -> alert()
                description.isEmpty() -> alert()
                harga.isEmpty() -> alert()

                else -> {
                    val price = harga.toInt()

                    if (variety == "Makanan"){
                        menuViewModel.insertFood(Food(name = menuName, description = description, price = price, image = 0))
                        startActivity(Intent(this, DataMenu::class.java))
                        finish()
                    }

                    else {
                        menuViewModel.insertBeverage(Beverage(name = menuName, description = description, price = price, image = 0))
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