package com.example.coffeshop.Adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeshop.Domain.CategoryModel
import com.example.coffeshop.R
import com.example.coffeshop.databinding.ViewholderCategoryBinding


class CategoryAdapter(val items : MutableList<CategoryModel>):
RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){
    private lateinit var context : Context
    private var selectedItemPosition = -1;
    private var lastSelectedItemPosition = -1;
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        context = parent.context;
        val binding = ViewholderCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        val item = items[position];
        holder.binding.titleCategory.text = item.title;
        holder.binding.root.setOnClickListener {
            lastSelectedItemPosition = selectedItemPosition;
            selectedItemPosition = position;
            notifyItemChanged(lastSelectedItemPosition);
            notifyItemChanged(selectedItemPosition)

            Handler(Looper.getMainLooper()).postDelayed({

            }, 500)
        }

        if(selectedItemPosition == position){
            holder.binding.titleCategory.setBackgroundResource(R.drawable.brown_full_corner_bg)
            holder.binding.titleCategory.setTextColor(context.resources.getColor(R.color.white))
        }else{
            holder.binding.titleCategory.setBackgroundResource(R.drawable.white_full_corner_bg)
            holder.binding.titleCategory.setTextColor(context.resources.getColor(R.color.darkBrown))
        }
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    class CategoryViewHolder(val binding: ViewholderCategoryBinding) : RecyclerView.ViewHolder(binding.root)

}