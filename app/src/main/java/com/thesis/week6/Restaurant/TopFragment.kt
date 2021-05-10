package com.thesis.week6.Restaurant

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.thesis.week6.AddRestaurantData
import com.thesis.week6.R
import com.thesis.week6.RemoveRestaurantData
import com.thesis.week6.databinding.FragmentFavBinding
import com.thesis.week6.setFavouriteChecked
import kotlinx.android.synthetic.main.fragment_fav.rcList

class TopFragment : Fragment() {
    private lateinit var ResAdapter: RestaurantAdapter
    private lateinit var binding : FragmentFavBinding
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
        rcList.apply {
            ResAdapter = RestaurantAdapter()
            adapter = ResAdapter
            ResAdapter.listener = object : RestaurantAdapter.RestaurantAdapterListener {
                override fun onClickCheckBox(Res: Restaurant, isChecked:Boolean) {
                    if (isChecked) {
                        AddRestaurantData(Res)
                    } else {
                        RemoveRestaurantData(Res)
                    }
                }
            }
            ResAdapter.data = setFavouriteChecked()
        }
    }

}

