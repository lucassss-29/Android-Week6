package com.thesis.week6.startup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thesis.week6.R
import com.google.android.material.button.MaterialButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class WelcomeFragment : Fragment() {
    private lateinit var btnStart : MaterialButton
    private lateinit var btnSignIn: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_welcome, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnStart = view.findViewById(R.id.btn_start)
        btnSignIn = view.findViewById(R.id.btn_sign_in)

        btnStart.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
                replace<SignUpFragment>(R.id.fragment_container_view)
                addToBackStack(null)
            }
        }

        btnSignIn.setOnClickListener {
            Log.e("son","sign in button")
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
                replace<SignInFragment>(R.id.fragment_container_view)
                addToBackStack(null)
            }
        }
    }

    }
