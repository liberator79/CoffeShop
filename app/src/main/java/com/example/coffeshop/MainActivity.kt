package com.example.coffeshop

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffeshop.Adapter.CategoryAdapter
import com.example.coffeshop.Adapter.PopularAdapter
import com.example.coffeshop.ViewModel.MainViewModel
import com.example.coffeshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding;
    private val viewModel = MainViewModel();
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initBanner();
        initCategory();
        initPopular()
    }

    private fun initBanner(){
        binding.BannerProgressBar.visibility = View.VISIBLE

        viewModel.loadBanner().observe(this) { list ->
            Glide.with(this)
                .load(list[0].url)
                .into(binding.banner)
            binding.BannerProgressBar.visibility = View.GONE
        }
        viewModel.loadBanner();
    }

    private fun initCategory(){
        binding.categoryProgressBar.visibility = View.GONE
        viewModel.loadCategory().observeForever {
            binding.categoryView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.categoryView.adapter = CategoryAdapter(it);
        }
        viewModel.loadCategory()
    }

    private fun initPopular(){
        binding.popularProgressBar.visibility = View.VISIBLE
        viewModel.loadPopular().observeForever {
            binding.popularView.layoutManager = GridLayoutManager(this, 2);
            binding.popularView.adapter = PopularAdapter(it);
            binding.popularProgressBar.visibility = View.GONE
        }
        viewModel.loadPopular()
    }


}