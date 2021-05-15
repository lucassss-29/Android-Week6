package com.thesis.week6.Restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thesis.week6.R

class RestaurantAdapter:RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

//    private lateinit var mLayoutManager : GridLayoutManager
    private lateinit var view : View
    private val LIST_ITEM = 0
    private val GRID_ITEM = 1
    var isSwitchView : Boolean = true

    interface RestaurantAdapterListener{
        fun onClickCheckBox(Res: Restaurant, isChecked:Boolean)
    }
    var  listener : RestaurantAdapterListener? = null
    var data: List<Restaurant> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == GRID_ITEM)
        {
            view = layoutInflater.inflate(R.layout.restaurant_grid_view, parent, false)
        } else {
            view = layoutInflater.inflate(R.layout.restaurant_item_view, parent, false)
        }
        return ViewHolder(view)
    }

    fun toggleItemViewType():Boolean{
        isSwitchView = !isSwitchView
        return isSwitchView
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.tvResName.text = item.name
        holder.tvResAddress.text = item.address
        Glide.with(view)
            .load(item.avatar)
            .into(holder.imgAvatar)
//        holder.imgAvatar.setImageResource(item.avatar)
        holder.heartBox.setChecked(item.fav)
        holder.heartBox!!.setOnCheckedChangeListener{buttonView, isChecked ->
            listener?.onClickCheckBox(item,isChecked)
            item.fav = isChecked
        }

    }
    override fun getItemCount(): Int {
        return data.size
    }
    override fun getItemViewType(position: Int): Int {
//        return if (mLayoutManager?.spanCount == 1) ViewType.LIST.ordinal
//        else ViewType.GRID.ordinal
        if (isSwitchView){
            return LIST_ITEM
        } else {
            return GRID_ITEM
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvResName = itemView.findViewById<TextView>(R.id.tv_res_name)
        val tvResAddress = itemView.findViewById<TextView>(R.id.tv_res_add)
        val imgAvatar = itemView.findViewById<ImageView>(R.id.iv_res)
        var heartBox = itemView.findViewById<CheckBox>(R.id.Heartbox)
    }


}