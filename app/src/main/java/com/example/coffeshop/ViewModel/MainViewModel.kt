package com.example.coffeshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coffeshop.Domain.BannerModel
import com.example.coffeshop.Domain.CategoryModel
import com.example.coffeshop.Domain.ItemsModel
import com.example.coffeshop.Repository.MainRepo

class MainViewModel : ViewModel() {
    private val repo = MainRepo()
    fun loadBanner() : LiveData<MutableList<BannerModel>>{
        return repo.loadBanner();
    }

    fun loadCategory() : LiveData<MutableList<CategoryModel>>{
        return repo.loadCategory();
    }

    fun loadPopular() : LiveData<MutableList<ItemsModel>>{
        return repo.loadPopular();
    }

    fun loadItems(categoryId : String) : LiveData<MutableList<ItemsModel>>{
        return repo.loadItemCategory(categoryId)
    }
}