package com.example.coffeshop

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.bumptech.glide.Glide
import com.example.coffeshop.Activity.CartActivity
import com.example.coffeshop.Adapter.BannerAdapter
import com.example.coffeshop.Adapter.CategoryAdapter
import com.example.coffeshop.Adapter.PopularAdapter
import com.example.coffeshop.ViewModel.MainViewModel
import com.example.coffeshop.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding;
    private val viewModel = MainViewModel();
    private lateinit var bannerAdapter: BannerAdapter
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
        initPopular();
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.apply {
            cartButton.setOnClickListener {
                var intent = Intent(this@MainActivity, CartActivity::class.java);
                startActivity(intent);
            }
        }
    }

    private fun initBanner(){
        binding.BannerProgressBar.visibility = View.VISIBLE

        viewModel.loadBanner().observe(this) { list ->
            bannerAdapter = BannerAdapter(list);
            binding.bannerView.adapter = bannerAdapter
            val transformer = CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))  // 40dp margin between pages
                addTransformer { page, position ->
                    val scale = 0.85f + (1 - abs(position)) * 0.15f
                    page.scaleY = scale
                }
            }
            binding.bannerView.setPageTransformer(transformer)
            //TabLayoutMediator(binding.tabLayout, binding.bannerView) { _, _ -> }.attach()
            binding.BannerProgressBar.visibility = View.GONE
        }
        viewModel.loadBanner();
    }

    private fun initCategory(){
        binding.categoryProgressBar.visibility = View.GONE
        viewModel.loadCategory().observeForever {
            binding.categoryView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.categoryView.adapter = CategoryAdapter(it);
            //
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