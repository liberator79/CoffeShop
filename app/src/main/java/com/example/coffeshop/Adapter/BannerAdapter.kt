package com.example.coffeshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeshop.Domain.BannerModel
import com.example.coffeshop.databinding.ViewholderBannerBinding

class BannerAdapter(private val images : MutableList<BannerModel>) :
RecyclerView.Adapter<BannerAdapter.BannerViewHolder>(){
    private lateinit var context : Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BannerAdapter.BannerViewHolder {
        context = parent.context;
        val binding = ViewholderBannerBinding.inflate(LayoutInflater.from(parent.context),parent, false);
        return BannerViewHolder(binding);
    }

    override fun onBindViewHolder(holder: BannerAdapter.BannerViewHolder, position: Int) {
        val image = images[position];
       Glide.with(context)
           .load(image.url)
           .into(holder.binding.banner);
    }

    class BannerViewHolder(val binding: ViewholderBannerBinding) : RecyclerView.ViewHolder(binding.root);

    override fun getItemCount(): Int {
        return images.size;
    }

}