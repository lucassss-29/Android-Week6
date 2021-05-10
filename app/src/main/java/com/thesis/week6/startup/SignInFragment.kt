package com.thesis.week6.startup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thesis.week6.Movie.MovieActivity
import com.thesis.week6.R
import com.thesis.week6.Restaurant.RestaurantActivity
import com.thesis.week6.UserInfo.User
import com.thesis.week6.databinding.ActivitySignInBinding


class SignInFragment : Fragment() {

    companion object {
        const val USER_KEY = "USER_KEY"
    }

    private lateinit var binding: ActivitySignInBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_sign_in, container, false)
        val view : View = binding.root
        viewModel = ViewModelProvider(requireActivity()).get(SignInViewModel::class.java)
        binding.lifecycleOwner = this
        binding.signInViewModel = viewModel
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupViewModelBinding()
        binding.apply {
            btnSignUp.setOnClickListener {
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                    replace<SignUpFragment>(R.id.fragment_container_view)
                    addToBackStack(null)
                }
            }

        }

        viewModel.isSignInSucceed.observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                showToastMessage("Sign in Successful")
//                startRestaurantActivity(user)
                startMovieActivity()
            }

        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
            message?.let {
                showToastMessage(message)
            }
        })


    }

        private fun startRestaurantActivity(user: User) {
        val bundle = Bundle()
        bundle.putParcelable(USER_KEY, user)
            val intent = Intent(activity, RestaurantActivity::class.java)
            startActivity(intent)
        }

        private fun startMovieActivity(){
            val intent = Intent(activity, MovieActivity::class.java)
            startActivity(intent)
        }

        private fun showToastMessage(value: String) {
            Toast.makeText(requireActivity(), value, Toast.LENGTH_SHORT).show()
        }

        override fun onStop() {
            super.onStop()
            viewModel.clear()
        }
    }

