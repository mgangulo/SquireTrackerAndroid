package com.mabinogifanmade.squiretracker.activitiesfragments.splash


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.utils.ShrdPrfsUtils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return View(activity).apply {
            visibility = View.GONE
        }
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (context!=null) {
            val directions = when (ShrdPrfsUtils.userDataExist(requireContext())) {
                true -> SplashFragmentDirections.navigateToMain()
                else -> SplashFragmentDirections.navigateToOnBoarding()
            }
            findNavController().navigate(directions,
                NavOptions.Builder()
                    .setPopUpTo(R.id.splashFragment,
                        true).build())
            requireActivity().finish()
        }
    }
}
