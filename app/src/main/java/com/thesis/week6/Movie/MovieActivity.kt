package com.thesis.week6.Movie

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.thesis.week6.R
import com.thesis.week6.Restaurant.Restaurant
import com.thesis.week6.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieBinding
    private lateinit var viewModel: MovieViewModel

    private lateinit var adapter : MovieAdapter
    private lateinit var recyclerView: RecyclerView
    private var count : Int = 0
    var data: List<NowPlayingResult> = listOf()
        set(value) {
            field = value
        }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        adapter = MovieAdapter()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<TopRatedFragment>(R.id.fragment_movie_container_view)
            addToBackStack(null)

        }
        binding.navigationMovieView.setOnNavigationItemSelectedListener { item->
            when(item.itemId){
                R.id.navigation_nowplaying ->{
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        replace<NowPlayingFragment>(R.id.fragment_container_view)
                        addToBackStack(null)

                    }
                    true
                }
                R.id.navigation_toprated->{
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        replace<TopRatedFragment>(R.id.fragment_container_view)
                        addToBackStack(null)
                    }
                    true
                }
                else -> false
            }
        }
        setupToolbar()
    }
    override fun onStart() {
        super.onStart()
        viewModel.getNowPlaying()
    }
    fun setupToolbar(){
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Movie"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return true
    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.change_layout -> {
//                val isLinearSwitched : Boolean = adapter.toggleItemViewType()
//                if (isLinearSwitched){
//                    rcList.layoutManager = LinearLayoutManager(this)
//                    item.title = "GRID"
//                }
//                else {
//                    rcList.layoutManager = GridLayoutManager(this,2)
//                    item.title = "LIST"
//                }
//            }
//        }
//        return  true
//    }
}