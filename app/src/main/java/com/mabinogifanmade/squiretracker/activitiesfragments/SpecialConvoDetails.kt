package com.mabinogifanmade.squiretracker.activitiesfragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.adapters.SpecialConvoAdapter
import com.mabinogifanmade.squiretracker.squiredata.Squire
import kotlinx.android.synthetic.main.fragment_special_convo_details.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class SpecialConvoDetails : BaseFragment() {
    val args: SpecialConvoDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_special_convo_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val squire: Squire = args.squire
        listener?.updateTitles(getString(R.string.squire_special,squire.squireName))
        if (context!=null) {
            specialConvoRecycler.adapter = SpecialConvoAdapter(requireContext(), squire.specialOptions, squire.hasHint)
        }
    }
}
