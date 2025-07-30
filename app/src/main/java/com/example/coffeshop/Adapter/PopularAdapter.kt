package com.example.coffeshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeshop.Domain.ItemsModel
import com.example.coffeshop.databinding.ViewholderPopularBinding

class PopularAdapter(val items : MutableList<ItemsModel>) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>(){
        private lateinit var context : Context;
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAdapter.PopularViewHolder {
        context = parent.context
        val binding = ViewholderPopularBinding.inflate(LayoutInflater.from(parent.context),parent, false);
        return PopularViewHolder(binding);
    }

    override fun onBindViewHolder(holder: PopularAdapter.PopularViewHolder, position: Int) {
        val item = items[position];
        holder.binding.titleview.text = item.title;
        Glide.with(context)
            .load(item.picUrl[0])
            .into(holder.binding.pic);
        holder.binding.subtitle.text = item.extra;
        holder.binding.price.text = "$ "+item.price.toString();
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class PopularViewHolder(val binding : ViewholderPopularBinding) : RecyclerView.ViewHolder(binding.root);
}