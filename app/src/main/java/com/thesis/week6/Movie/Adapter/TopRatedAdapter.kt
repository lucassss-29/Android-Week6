package com.thesis.week6.Movie.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thesis.week6.Movie.TopRated.TopRatedResult
import com.thesis.week6.R

public var t_isSwitchView: Boolean = true
class TopRatedAdapter : RecyclerView.Adapter<TopRatedAdapter.ViewHolder>() {

    private lateinit var view: View
    private val GRID_ITEM = 1
    private val LIST_ITEM = 0

    interface TopRatedAdapterListener{
        fun OnClickItem(Mov: TopRatedResult)
    }

    var listener: TopRatedAdapterListener? = null

    var data: List<TopRatedResult> = listOf()
    set(value){
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedAdapter.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == GRID_ITEM){
            view = layoutInflater.inflate(R.layout.movie_grid_view, parent, false)
        } else {
            view = layoutInflater.inflate(R.layout.movie_list_view, parent, false)
        }
        return ViewHolder(view)
    }

    fun toogleItemViewType(): Boolean{
        t_isSwitchView = !t_isSwitchView
        Log.e("t_isSwitchView = ", t_isSwitchView.toString())
        return t_isSwitchView
    }

    override fun getItemViewType(position: Int): Int {
        if (t_isSwitchView){
            return LIST_ITEM
        } else {
            return GRID_ITEM
        }
    }

    override fun onBindViewHolder(holder: TopRatedAdapter.ViewHolder, position: Int) {
        holder.tvMovieTitle.text = data[position].title
        holder.tvMovieDate.text = data[position].releaseDate
        holder.tvMovieViewer.text = data[position].popularity.toString()
        holder.tvMovieSection.text = "Top Rated"
        val img_poster = "https://image.tmdb.org/t/p/w500" + data[position].posterPath
        Glide.with(view)
            .load(img_poster)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.imgAvatar)
        holder.itemView.setOnClickListener {
            listener?.OnClickItem(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvMovieTitle = itemView.findViewById<TextView>(R.id.tv_mv_title)
        val tvMovieDate = itemView.findViewById<TextView>(R.id.tv_mv_date)
        val tvMovieViewer = itemView.findViewById<TextView>(R.id.tv_mv_viewer)
        val imgAvatar = itemView.findViewById<ImageView>(R.id.iv_mv_avatar)
        val tvMovieSection = itemView.findViewById<TextView>(R.id.tv_mv_section)
    }

}