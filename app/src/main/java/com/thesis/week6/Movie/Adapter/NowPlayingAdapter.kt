package com.thesis.week6.Movie.Adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thesis.week6.Movie.NowPlaying.NowPlayingResult
import com.thesis.week6.R
public var isSwitchView: Boolean = true
class NowPlayingAdapter: RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    private lateinit var view: View
    var nListener: NowPlayingAdapterListener ?= null
    private val GRID_ITEM = 1
    private val LIST_ITEM = 0

    interface NowPlayingAdapterListener{
        fun nOnClickItem(nMov: NowPlayingResult)
    }

    var nData: List<NowPlayingResult> = listOf()
    set(value){
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowPlayingAdapter.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == GRID_ITEM) {
            view = layoutInflater.inflate(R.layout.movie_grid_view, parent, false)
        } else {
            view = layoutInflater.inflate(R.layout.movie_list_view, parent, false)
        }
        return ViewHolder(view)
    }

    fun toogleItemViewType(): Boolean {
        isSwitchView = !isSwitchView
        Log.e("isSwitchView = ", isSwitchView.toString())
        return isSwitchView
    }

    override fun getItemViewType(position: Int): Int {
        if (isSwitchView){
            return LIST_ITEM
        } else {
            return GRID_ITEM
        }
    }

    override fun onBindViewHolder(holder: NowPlayingAdapter.ViewHolder, position: Int) {
        holder.tvMovieTitle.text = nData[position].title
        holder.tvMovieDate.text = nData[position].releaseDate
        holder.tvMovieViewer.text = nData[position].popularity.toString()
        holder.tvMovieSection.text = "Now Playing ..."

        val img_poster = "https://image.tmdb.org/t/p/w500" + nData[position].posterPath
        Glide.with(view)
            .load(img_poster)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.imgAvatar)
        holder.itemView.setOnClickListener {
            nListener?.nOnClickItem(nData[position])
        }
    }

    override fun getItemCount(): Int {
        return nData.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvMovieTitle = itemView.findViewById<TextView>(R.id.tv_mv_title)
        val tvMovieDate = itemView.findViewById<TextView>(R.id.tv_mv_date)
        val tvMovieViewer = itemView.findViewById<TextView>(R.id.tv_mv_viewer)
        val imgAvatar = itemView.findViewById<ImageView>(R.id.iv_mv_avatar)
        val tvMovieSection = itemView.findViewById<TextView>(R.id.tv_mv_section)
    }

}