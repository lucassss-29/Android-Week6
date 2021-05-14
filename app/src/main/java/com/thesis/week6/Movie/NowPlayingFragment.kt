package com.thesis.week6.Movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thesis.week6.R
import com.thesis.week6.databinding.FragmentFavBinding
import kotlinx.android.synthetic.main.fragment_fav.*
public lateinit var viewModel1: MovieViewModel
class NowPlayingFragment: Fragment() {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: FragmentFavBinding

    private lateinit var Adapter : MovieAdapter
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
        viewModel1 = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel1.run {
            Log.e("Tile--------------->",listdata.toString())
            listdata.observe(viewLifecycleOwner, Observer {
                rcList.apply {
                    Adapter = MovieAdapter()
                    adapter = Adapter
                    Adapter.data = it
                    Adapter.listener = object : MovieAdapter.MovieAdapterListener{
                        override fun onClickItem(Mov: NowPlayingResult) {
                            setdataNowPlaying(Mov)
                            childFragmentManager.commit {
                                setReorderingAllowed(true)
                                setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                                replace<DetailInformationFragment>(R.id.fragment_Inf_container_view)
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
        viewModel1.getNowPlaying()
    }

    override fun onDestroy() {
        super.onDestroy()
        Adapter.listener = null
    }
}
