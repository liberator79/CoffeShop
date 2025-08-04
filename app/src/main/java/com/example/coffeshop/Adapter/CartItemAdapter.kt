package com.example.coffeshop.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeshop.Domain.ItemsModel
import com.example.coffeshop.Helper.ChangeNumberItemsListener
import com.example.coffeshop.Helper.ManagmentCart
import com.example.coffeshop.databinding.ViewholderCartBinding
import kotlin.math.max
import android.R
import kotlin.math.min

class CartItemAdapter(
    private val listitemselected : ArrayList<ItemsModel>,
    var context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener? = null
) :
RecyclerView.Adapter<CartItemAdapter.CartItemViewholder>(){
    private val managementCart = ManagmentCart(context);
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartItemAdapter.CartItemViewholder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return CartItemViewholder(binding)
    }

    override fun onBindViewHolder(
        holder: CartItemAdapter.CartItemViewholder,
        position: Int
    ) {


        val item = listitemselected[position];
        holder.binding.textView20.text = item.title;
        holder.binding.textView22.text = "$${item.price}";
        holder.binding.textView21.text = "$${item.numberInCart * item.price}";
        holder.binding.totalItems.text = item.numberInCart.toString();
        Glide.with(context)
            .load(item.picUrl[0])
            .into(holder.binding.coffeePic)
      //  holder.binding.coffeePic.setImageResource(R.drawable.btn_plus)
        holder.binding.increaseItem.setOnClickListener {
            managementCart.plusItem(listitemselected, position, object : ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged();
                    changeNumberItemsListener?.onChanged()
                }
            })

        }

        holder.binding.decreaseItem.setOnClickListener {
            managementCart.minusItem(listitemselected, position, object : ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged();
                    changeNumberItemsListener?.onChanged()
                }
            })

        }

        holder.binding.remove.setOnClickListener {
            managementCart.removeItem(listitemselected, position, object : ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged();
                    changeNumberItemsListener?.onChanged()
                }
            })
        }

    }

    override fun getItemCount(): Int {
        return listitemselected.size
    }

    class CartItemViewholder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root);
}

/*


 */