package com.mabinogifanmade.squiretracker


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.utils.ConversationUtils
import com.mabinogifanmade.squiretracker.utils.UserUtils
import kotlinx.android.synthetic.main.fragment_squire_details.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SquireDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SquireDetailsFragment : Fragment() {
    val args: SquireDetailsFragmentArgs by navArgs()
    lateinit var squire: Squire
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_squire_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (context != null) {
            squire = args.squire
            squireName.setText(squire.squireName)
            val progress:Int = UserUtils.getCurrentCharProgressForSquire(context!!,squire.id)
            sequenceText.setText(
                ConversationUtils.translateCurrentAbv(squire,false,progress)
            )
            if (squire.hasHint){
                hintText.setText(
                    ConversationUtils.translateCurrentAbv(squire,true,progress)
                )
                hintText.visibility=View.VISIBLE
            } else {
                hintText.visibility=View.GONE
            }
        }
    }
}
