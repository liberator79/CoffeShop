package com.example.coffeshop.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coffeshop.Adapter.ItemAdapter
import com.example.coffeshop.Adapter.PopularAdapter
import com.example.coffeshop.R
import com.example.coffeshop.databinding.FragmentItemsBinding


class ItemsFragment : Fragment(R.layout.fragment_items) {
    lateinit var binding : FragmentItemsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initItems();
    }
    private fun initItems(){

    }
}