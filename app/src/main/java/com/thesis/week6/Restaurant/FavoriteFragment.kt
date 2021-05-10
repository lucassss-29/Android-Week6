package com.thesis.week6.Restaurant

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.thesis.week6.GetRestaurantData
import com.thesis.week6.R
import kotlinx.android.synthetic.main.fragment_fav.*

class FavoriteFragment: Fragment() {
    private lateinit var adapter1 : RestaurantAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fav,container,false)
        return view
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        rcList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter1 = RestaurantAdapter()
            adapter = adapter1
            adapter1.data = GetRestaurantData()
            }
        }
    }
