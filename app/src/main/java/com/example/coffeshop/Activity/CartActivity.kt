package com.example.coffeshop.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeshop.Adapter.CartItemAdapter
import com.example.coffeshop.Helper.ChangeNumberItemsListener
import com.example.coffeshop.Helper.ManagmentCart
import com.example.coffeshop.R
import com.example.coffeshop.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    lateinit var binding : ActivityCartBinding
    lateinit var managmentCart: ManagmentCart;
    private var tax : Double = 0.0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        managmentCart = ManagmentCart(this);
        calculateCart()
        setVariable()
        initCartList()
    }

    private fun initCartList() {
        binding.apply{
            listView.layoutManager = LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false);
            listView.adapter = CartItemAdapter(
                managmentCart.getListCart(),
                this@CartActivity,
                object : ChangeNumberItemsListener{
                    override fun onChanged() {
                        calculateCart()
                    }
                }
            )
        }
    }

    private fun setVariable() {
        binding.linearLayout2.setOnClickListener { finish() }
    }

    private fun calculateCart() {
        var tax = Math.round((managmentCart.getTotalFee()*18)/100);
        binding.textView17.text = "$"+tax.toString()
        binding.textView13.text = "$${15}";
        binding.textView12.text = "$${managmentCart.getTotalFee()}"
        val total = managmentCart.getTotalFee() + tax + 15
        binding.textView18.text = "$${Math.round(total)}"

    }
}