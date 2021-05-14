package com.thesis.week6.Movie

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thesis.week6.R
import com.thesis.week6.databinding.InformationFragmentBinding
import kotlinx.android.synthetic.main.information_fragment.*


class DetailInformationFragment : Fragment(){
    private lateinit var binding : InformationFragmentBinding
    private lateinit var Adapter : MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.information_fragment, container, false)
        return view
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        viewModel1.run {
            data.observe(viewLifecycleOwner, Observer {
                Tile.text = it.title.toString()
                ReleaseDate.text = it.releaseDate.toString()
                Story.text = it.overview.toString()

                btnStar.text = it.voteAverage.toString()
                val media = "https://image.tmdb.org/t/p/w500" + it.posterPath.toString()
                Glide.with(itemView)
                    .load(media)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(MovieImg)
                Log.e("Tag------------------",it.title.toString())
            })
        }
        Story.movementMethod = ScrollingMovementMethod()
        btnBack.setOnClickListener {
            getFragmentManager()?.popBackStack()
        }


    }
}