package com.thesis.week6.Movie.Detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thesis.week6.Movie.NowPlaying.mViewModel
import com.thesis.week6.R
import kotlinx.android.synthetic.main.n_detail_movie_fragment.*

class NowPlayingDetailFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.n_detail_movie_fragment, container, false)
        return mView
    }

    override fun onViewCreated(mView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(mView, savedInstanceState)
        mViewModel.run{
            nowPlayingData.observe(viewLifecycleOwner, Observer {
                n_tv_movie_title.text = it.title
                n_tv_movie_overview.text = it.overview
                n_tv_movie_date.text = it.releaseDate
                n_tv_movie_point.text = it.popularity.toString()
                val img_poster = "https://image.tmdb.org/t/p/w500" + it.posterPath.toString()
                val img_backdrop = "https://image.tmdb.org/t/p/w500" + it.backdropPath.toString()
                Glide.with(mView)
                    .load(img_poster)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(n_img_movie_poster)
                Glide.with(mView)
                    .load(img_backdrop)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(n_img_movie_backdrop)
            })
        }
        n_img_movie_back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}