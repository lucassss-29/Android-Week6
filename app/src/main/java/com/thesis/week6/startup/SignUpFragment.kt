package com.thesis.week6.startup

import android.os.Bundle
import android.util.Log
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
import com.thesis.week6.R
import com.thesis.week6.databinding.ActivitySignUpBinding

class SignUpFragment : Fragment() {
    private lateinit var binding : ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_sign_up, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)
//        binding.setLifecycleOwner (activity)
        binding.lifecycleOwner = this
        binding.signUpViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupViewModelBinding()
        binding.btnSignUp.setOnClickListener {
            Log.e("son", "sign in button")
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                replace<SignInFragment>(R.id.fragment_container_view)
                addToBackStack(null)
            }

        }

        binding.btnSignIn.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                replace<SignInFragment>(R.id.fragment_container_view)
                addToBackStack(null)
            }
        }

        viewModel.isSignUpSucceed.observe(viewLifecycleOwner, Observer<Boolean> {
            it?.let {
                if (it) {
                    showToastMessage("Sign Up Successful")
                    startSignInActivity()
                }
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer<String> { message ->
            message?.let {
                Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.clear()
    }


    private fun startSignInActivity() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
            replace<SignInFragment>(R.id.fragment_container_view)
            addToBackStack(null)
        }
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }
}