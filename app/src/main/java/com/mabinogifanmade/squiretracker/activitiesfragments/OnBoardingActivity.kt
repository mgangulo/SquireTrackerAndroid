package com.mabinogifanmade.squiretracker.activitiesfragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mabinogifanmade.squiretracker.R

class OnBoardingActivity : AppCompatActivity(),
    BaseFragment.OnBaseFragmentListener{
    override fun updateTitles(title: String, subtitle: String) {
        supportActionBar?.setTitle(title)
        supportActionBar?.setSubtitle(subtitle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
    }
}
