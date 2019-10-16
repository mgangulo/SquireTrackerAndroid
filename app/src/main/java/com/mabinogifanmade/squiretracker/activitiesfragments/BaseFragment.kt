package com.mabinogifanmade.squiretracker.activitiesfragments


import android.content.Context
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
open class BaseFragment : Fragment() {

    protected var listener: OnBaseFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBaseFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnBaseFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnBaseFragmentListener {
        fun updateTitles(title:String,subtitle:String = "")
    }


}
