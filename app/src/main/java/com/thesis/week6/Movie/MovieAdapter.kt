package com.thesis.week6.Movie

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thesis.week6.R
import com.thesis.week6.Movie.NowPlayingResult
import com.thesis.week6.R.id.tvResName

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
    //    private lateinit var mLayoutManager : GridLayoutManager
    private lateinit var view : View
    private val LIST_ITEM = 0
    private val GRID_ITEM = 1
    var isSwitchView : Boolean = true

    interface MovieAdapterListener{
        fun onClickItem(Mov: NowPlayingResult)
    }
    var  listener : MovieAdapterListener? = null
    var data: List<NowPlayingResult> = listOf()
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

        holder.tvResName.text = item.title
        holder.tvResAddress.text = item.overview
        Log.e("Tile--------------->", item.title.toString())
        val media = "https://image.tmdb.org/t/p/w500" + item.posterPath
        Glide.with(view)
            .load(media)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.imgAvatar)
//       holder.imgAvatar.setImageResource(item.avatar)
        holder.itemView.setOnClickListener {
            listener?.onClickItem(item)
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
        val tvResName = itemView.findViewById<TextView>(R.id.tvResName)
        val tvResAddress = itemView.findViewById<TextView>(R.id.tvResAddress)
        val imgAvatar = itemView.findViewById<ImageView>(R.id.imageView)
       // var heartBox = itemView.findViewById<CheckBox>(R.id.Heartbox)

    }

}