package com.example.kasirapp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kasirapp.data.db.KasirDatabase
import com.example.kasirapp.data.entity.Beverage
import com.example.kasirapp.data.entity.Food
import com.example.kasirapp.data.repo.KasirRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuViewModel(application: Application):AndroidViewModel(application) {
    val getFood: LiveData<List<Food>>
    val getBeverage: LiveData<List<Beverage>>
    val repository : KasirRepository

    init {
        val dao = KasirDatabase.getDatabase(application).getKasirDao()
        repository = KasirRepository(dao)
        getFood = repository.foods
        getBeverage = repository.beverages
    }

    fun insertFood(food: Food) = viewModelScope.launch(Dispatchers.IO){
        repository.insertFood(food)
    }

    fun deleteFood(food: Food) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteFood(food)
    }

    fun updateFood(food: Food) = viewModelScope.launch(Dispatchers.IO){
        repository.updateFood(food)
    }

    fun insertBeverage(beverage: Beverage) = viewModelScope.launch(Dispatchers.IO){
        repository.insertBeverage(beverage)
    }

    fun deleteBeverage(beverage: Beverage) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteBeverage(beverage)
    }

    fun updateBeverage(beverage: Beverage) = viewModelScope.launch(Dispatchers.IO){
        repository.updateBeverage(beverage)
    }
}
