package com.thesis.week6.startup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.thesis.week6.Onboarding.Onboarding1Fragment
import com.thesis.week6.R

//import com.thesis.week5.Onboarding.Onboarding1Activity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            setContentView(R.layout.activity_main)
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                add<Onboarding1Fragment>(R.id.fragment_container_view)
            }
        }, 3000)
    }
}