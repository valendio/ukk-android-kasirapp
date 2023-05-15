package com.example.kasirapp.data.repo

import androidx.lifecycle.LiveData
import com.example.kasirapp.data.dao.KasirDao
import com.example.kasirapp.data.entity.*

class KasirRepository(private val kasirDao: KasirDao){
    val foods : LiveData<List<Food>> = kasirDao.getFood()
    val beverages : LiveData<List<Beverage>> = kasirDao.getBeverage()
    val users : LiveData<List<User>> = kasirDao.getUser()
    val tables : LiveData<List<Meja>> = kasirDao.getMeja()

    suspend fun insertFood(food: Food){
        kasirDao.insertFood(food)
    }

    suspend fun deleteFood(food: Food){
        kasirDao.deleteFood(food)
    }

    suspend fun updateFood(food: Food){
        kasirDao.updateFood(food)
    }

    suspend fun insertBeverage(beverage: Beverage){
        kasirDao.insertBeverage(beverage)
    }

    suspend fun deleteBeverage(beverage: Beverage){
        kasirDao.deleteBeverage(beverage)
    }

    suspend fun updateBeverage(beverage: Beverage){
        kasirDao.updateBeverage(beverage)
    }

    suspend fun insertUser(user: User){
        kasirDao.insertUser(user)
    }

    suspend fun deleteUser(user: User){
        kasirDao.deleteUser(user)
    }

    suspend fun updateUser(user: User){
        kasirDao.updateUser(user)
    }

    suspend fun insertTable(meja: Meja){
        kasirDao.insertTable(meja)
    }

    suspend fun deleteTable(meja: Meja){
        kasirDao.deleteTable(meja)
    }

    suspend fun updateTable(meja: Meja){
        kasirDao.updateTable(meja)
    }
}