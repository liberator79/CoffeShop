package com.example.coffeshop.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeshop.Adapter.CategoryAdapter
import com.example.coffeshop.Adapter.ItemAdapter
import com.example.coffeshop.R
import com.example.coffeshop.ViewModel.MainViewModel
import com.example.coffeshop.databinding.ActivityItemsListBinding

class ItemsListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityItemsListBinding;
    private val viewModel = MainViewModel();
    private var id : String = "";
    private var title : String  = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityItemsListBinding.inflate(layoutInflater);
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getBundles()
        initList()
    }

    private fun getBundles(){
        id = intent.getStringExtra("id")?: throw IllegalArgumentException("id is required")
        title = intent.getStringExtra("title")?: throw IllegalArgumentException("title is required")
    }

    private fun initList(){
        //initCategory();
        binding.apply {
            viewModel.loadItems(id).observe(this@ItemsListActivity, Observer{
                listView.layoutManager = GridLayoutManager(this@ItemsListActivity, 2);
                listView.adapter = ItemAdapter(it);
            })
        }
        binding.titleVat.text = title
        binding.backbtn.setOnClickListener {
            finish();
        }
    }
//    private fun initCategory(){
//        binding.categoryProgressBar.visibility = View.GONE
//        viewModel.loadCategory().observeForever {
//            binding.categoryView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//            binding.categoryView.adapter = CategoryAdapter(it);
//        }
//        viewModel.loadCategory()
//    }
}