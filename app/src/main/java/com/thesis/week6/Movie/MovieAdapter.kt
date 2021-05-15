package com.thesis.week6.Movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thesis.week6.Movie.rest.MovieDBService
import com.thesis.week6.R
import com.thesis.week6.Restaurant.Restaurant

class MovieAdapter:RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    //    private lateinit var mLayoutManager : GridLayoutManager
    private lateinit var view : View
    private val LIST_ITEM = 0
    private val GRID_ITEM = 1
    var isSwitchView : Boolean = true

    interface RestaurantAdapterListener{
        fun onClickCheckBox(Res: Movie, isChecked:Boolean)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvMovieName = itemView.findViewById<TextView>(R.id.tvMovieName)
        val tvMovieDetail = itemView.findViewById<TextView>(R.id.tvMovieDetail)
        val imgAvatar = itemView.findViewById<ImageView>(R.id.imageView)
    }

    var  listener : MovieAdapter? = null
    var data: List<MovieDBService> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == GRID_ITEM)
        {
            view = layoutInflater.inflate(R.layout.movie_grid, parent, false)
        } else {
            view = layoutInflater.inflate(R.layout.movie_item_view, parent, false)
        }
        return ViewHolder(view)
    }

    fun toggleItemViewType():Boolean{
        isSwitchView = !isSwitchView
        return isSwitchView
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.tvMovieName.text = item.originalTitle
        holder.tvMovieDetail.text = item.overview
        //Add picture
      // Glide.with(view)
           // .load(item.avatar)
          //  .into(holder.imgAvatar)
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
    }

}