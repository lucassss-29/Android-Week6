package com.thesis.week6.Movie.Adapter

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

class TopRatedAdapter : RecyclerView.Adapter<TopRatedAdapter.ViewHolder>() {

    private lateinit var view: View

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
        view = layoutInflater.inflate(R.layout.movie_list_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopRatedAdapter.ViewHolder, position: Int) {
        holder.tvMovieTitle.text = data[position].title
        holder.tvMovieDate.text = data[position].releaseDate
        holder.tvMovieViewer.text = data[position].popularity.toString()
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
    }

}