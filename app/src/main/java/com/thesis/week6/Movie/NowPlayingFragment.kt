package com.thesis.week6.Movie

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.thesis.week6.AddRestaurantData
import com.thesis.week6.R
import com.thesis.week6.RemoveRestaurantData
import com.thesis.week6.Restaurant.Restaurant
import com.thesis.week6.Restaurant.RestaurantAdapter
import com.thesis.week6.databinding.FragmentFavBinding
import com.thesis.week6.setFavouriteChecked
import kotlinx.android.synthetic.main.fragment_fav.*

class NowPlayingFragment: Fragment() {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: FragmentFavBinding
    private lateinit var viewModel: MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fav, container, false)
        return view
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        rcList.apply {
            movieAdapter = MovieAdapter()
            adapter = movieAdapter
            movieAdapter.data = this@NowPlayingFragment.viewModel.getNowPlaying()
        }
    }
}
