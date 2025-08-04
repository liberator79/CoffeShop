package com.example.coffeshop.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.coffeshop.Domain.ItemsModel
import com.example.coffeshop.Helper.ManagmentCart
import com.example.coffeshop.R
import com.example.coffeshop.databinding.ActivityDetailsBinding
import kotlin.math.max
import kotlin.math.min

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailsBinding
    private lateinit var item : ItemsModel
    private lateinit var managementCart : ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        managementCart = ManagmentCart(this);
        bundle();

    }

    private fun bundle() {
        binding.apply {
            item = intent.getSerializableExtra("object") as ItemsModel;
            Glide.with(this@DetailsActivity)
                .load(item.picUrl[0])
                .into(binding.mainImg);
            binding.coffeTitle.text = item.title;
            binding.itemDescription.text = item.description;
            binding.itemPrice.text = "$ "+item.price.toString();
            binding.itemRating.text = item.rating.toString();
            binding.cartButton.setOnClickListener {
                if(item.numberInCart >= 1){
                    item.numberInCart = Integer.valueOf(totalItems.text.toString());
                    managementCart.insertItems(item);
                }else{
                    Toast.makeText(this@DetailsActivity, "Item size should be atleast 1", Toast.LENGTH_SHORT).show()
                }

            }
            binding.totalItems.text = item.numberInCart.toString()
            backbtn.setOnClickListener { finish() }

            increaseItem.setOnClickListener {
                totalItems.text = (min(10, item.numberInCart+1)).toString();
                item.numberInCart = min(10, item.numberInCart+1);
            }

            decreaseItem.setOnClickListener {
                totalItems.text = (max(0, item.numberInCart-1)).toString();
                item.numberInCart = max(0, item.numberInCart-1);
            }

        }
    }
}