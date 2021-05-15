package com.thesis.week6.Movie.Detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thesis.week6.Movie.TopRated.tViewModel
import com.thesis.week6.R
import kotlinx.android.synthetic.main.detail_movie_fragment.*

class TopRatedDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_movie_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            tViewModel.run {
                topRatedData.observe(viewLifecycleOwner, Observer {
                    tv_movie_title.text = it.title.toString()
                    tv_movie_date.text = it.releaseDate.toString()
                    tv_movie_overview.text = it.overview.toString()
                    tv_movie_point.text = it.voteAverage.toString()
                    val img_poster = "https://image.tmdb.org/t/p/w500" + it.posterPath.toString()
                    val img_backdrop =
                        "https://image.tmdb.org/t/p/w500" + it.backdropPath.toString()
                    Glide.with(view)
                        .load(img_backdrop)
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .into(img_movie_backdrop)
                    Glide.with(view)
                        .load(img_poster)
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .into(img_movie_poster)
                })
            }

        img_movie_back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}