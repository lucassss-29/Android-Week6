package com.thesis.week6.Movie.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.thesis.week6.Movie.NowPlaying.NowPlayingFragment
import com.thesis.week6.Movie.TopRated.TopRatedFragment
import com.thesis.week6.Movie.ViewModel.MovieViewModel
import com.thesis.week6.R
import com.thesis.week6.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding
    lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<NowPlayingFragment>(R.id.fragment_movie_container_view)
            addToBackStack(null)
        }

        binding.navigationMovieView.setOnNavigationItemSelectedListener {item ->
            when (item.itemId){
                R.id.navigation_nowplaying-> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<NowPlayingFragment>(R.id.fragment_movie_container_view)
                        addToBackStack(null)
                    }
                    true
                }
                R.id.navigation_toprated -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<TopRatedFragment>(R.id.fragment_movie_container_view)
                        addToBackStack(null)
                    }
                    true
                }
                else -> false
            }

        }
    }

    override fun onStart() {
        super.onStart()
    }

}