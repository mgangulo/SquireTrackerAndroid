package com.mabinogifanmade.squiretracker.activitiesfragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mabinogifanmade.squiretracker.R
import kotlinx.android.synthetic.main.fragment_privacy_policy.*

/**
 * A simple [Fragment] subclass.
 */
class PrivacyPolicyFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_privacy_policy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener?.updateTitles(getString(R.string.menu_privacy))
        webview.loadUrl("file:///android_asset/privacy_policy.html");
    }
}
