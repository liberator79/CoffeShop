package com.example.coffeshop.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeshop.Activity.DetailsActivity
import com.example.coffeshop.Domain.ItemsModel
import com.example.coffeshop.databinding.ViewholderItemsBinding

class ItemAdapter(val items : MutableList<ItemsModel>) :
RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
    lateinit var context : Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemAdapter.ItemViewHolder {
        context = parent.context;
        val binding = ViewholderItemsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemViewHolder(binding);
    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        val item = items[position];
        holder.binding.titleview.text = item.title
        holder.binding.price.text = "$ " + item.price.toString();
        Glide.with(context)
            .load(item.picUrl[0])
            .into(holder.binding.pic);
        //holder.binding.titleview.text = item.title;
        holder.binding.subtitle.text = item.extra

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java);
            intent.putExtra("object", item);
            context.startActivity(intent);
        }
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    class ItemViewHolder(val binding : ViewholderItemsBinding) : RecyclerView.ViewHolder(binding.root){}
};