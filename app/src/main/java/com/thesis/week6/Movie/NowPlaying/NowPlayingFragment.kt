package com.thesis.week6.Movie.NowPlaying

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.thesis.week6.Movie.Adapter.NowPlayingAdapter
import com.thesis.week6.Movie.Adapter.isSwitchView
import com.thesis.week6.Movie.Detail.NowPlayingDetailFragment
import com.thesis.week6.Movie.ViewModel.MovieViewModel
import com.thesis.week6.R
import kotlinx.android.synthetic.main.fragment_fav.*

public lateinit var mViewModel: MovieViewModel

class NowPlayingFragment: Fragment() {

    private lateinit var nAdapter: NowPlayingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fav, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        mViewModel.run{
            listNowPlayingData.observe(viewLifecycleOwner, Observer {
                rcList.apply {
                    nAdapter = NowPlayingAdapter()
                    adapter = nAdapter
                    if (isSwitchView == false ) {
                        layoutManager = GridLayoutManager(activity, 3)
                    } else {
                        layoutManager = LinearLayoutManager(activity)
                    }
                    nAdapter.nData = it
                    nAdapter.nListener = object: NowPlayingAdapter.NowPlayingAdapterListener{
                        override fun nOnClickItem(nMov: NowPlayingResult) {
                            setDataNowPlaying(nMov)
                            parentFragmentManager.commit {
                                setReorderingAllowed(true)
                                setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                                replace<NowPlayingDetailFragment>(R.id.fragment_movie_container_view)
                                addToBackStack(null)
                            }
                        }

                    }
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.change_layout -> {
                val isLinearSwitched: Boolean = nAdapter.toogleItemViewType()
                Log.e("isLinearSwitched =", isLinearSwitched.toString())
                if (isLinearSwitched){
                    rcList.layoutManager = LinearLayoutManager(activity)
                    Log.e("TAG", "LinearLayoutManager")
                } else {
                    rcList.layoutManager = GridLayoutManager(activity, 3)
                    Log.e("TAG", "GridLayoutManager")
                }
            }
        }
        return true
    }

    override fun onStart() {
        super.onStart()
        mViewModel.getNowPlayingData()
    }
}