package com.thesis.week6.Movie.TopRated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.thesis.week6.Movie.Adapter.TopRatedAdapter
import com.thesis.week6.Movie.Adapter.isSwitchView
import com.thesis.week6.Movie.Adapter.t_isSwitchView
import com.thesis.week6.Movie.Detail.TopRatedDetailFragment
import com.thesis.week6.Movie.ViewModel.MovieViewModel
import com.thesis.week6.R
import kotlinx.android.synthetic.main.fragment_fav.*

public lateinit var tViewModel: MovieViewModel

class TopRatedFragment : Fragment() {

    private lateinit var tAdapter: TopRatedAdapter

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
        tViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        tViewModel.run{
            listTopRatedData.observe(viewLifecycleOwner, Observer {
                rcList.apply {
                    tAdapter = TopRatedAdapter()
                    adapter = tAdapter
                    if (t_isSwitchView == false ) {
                        layoutManager = GridLayoutManager(activity, 3)
                    } else {
                        layoutManager = LinearLayoutManager(activity)
                    }
                    tAdapter.data = it
                    tAdapter.listener = object : TopRatedAdapter.TopRatedAdapterListener{
                        override fun OnClickItem(Mov: TopRatedResult) {
                            setDataTopRated(Mov)
                            childFragmentManager.commit {
                                setReorderingAllowed(true)
                                setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                                replace<TopRatedDetailFragment>(R.id.fragment_movie_detail_view)
                                addToBackStack(null)
                            }
                        }

                    }

                }
            })
        }
    }

    override fun onStart() {
        super.onStart()
        tViewModel.getTopRatedData()
    }

    override fun onDestroy() {
        super.onDestroy()
        tAdapter.listener = null
    }

}