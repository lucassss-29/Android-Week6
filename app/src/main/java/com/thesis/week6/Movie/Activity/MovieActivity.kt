package com.thesis.week6.Movie.Activity

import android.os.Bundle
import android.text.BoringLayout
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.thesis.week6.Movie.Adapter.NowPlayingAdapter
import com.thesis.week6.Movie.Adapter.TopRatedAdapter
import com.thesis.week6.Movie.Adapter.isSwitchView
import com.thesis.week6.Movie.Detail.NowPlayingDetailFragment
import com.thesis.week6.Movie.NowPlaying.NowPlayingFragment
import com.thesis.week6.Movie.NowPlaying.NowPlayingResult
import com.thesis.week6.Movie.NowPlaying.mViewModel
import com.thesis.week6.Movie.TopRated.TopRatedFragment
import com.thesis.week6.Movie.ViewModel.MovieViewModel
import com.thesis.week6.R
import com.thesis.week6.databinding.ActivityMovieBinding
import kotlinx.android.synthetic.main.fragment_fav.*

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding
    lateinit var movieViewModel: MovieViewModel
    private lateinit var nAdapter: NowPlayingAdapter
    private lateinit var tAdapter: TopRatedAdapter
    public var isLinearSwitched: Boolean = true
    public var t_isLinearSwitched: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)


        nAdapter = NowPlayingAdapter()
        tAdapter = TopRatedAdapter()
//        binding.rcList.adapter = nAdapter

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

        setupMovieToolbar()
    }

    override fun onStart() {
        super.onStart()
    }

    fun setupMovieToolbar(){
        val toolbar_mv: Toolbar = findViewById<View>(R.id.toolbar_movie) as Toolbar
        setSupportActionBar(toolbar_mv)
        supportActionBar?.title = "Movie"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.change_layout -> {
                isLinearSwitched = nAdapter.toogleItemViewType()
                Log.e("isLinearSwitched =", isLinearSwitched.toString())
                t_isLinearSwitched = tAdapter.toogleItemViewType()
                Log.e("t_isLinearSwitched =", t_isLinearSwitched.toString())
            }
        }
        return true
    }

}