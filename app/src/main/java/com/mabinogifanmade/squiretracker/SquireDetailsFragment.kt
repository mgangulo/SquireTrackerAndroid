package com.mabinogifanmade.squiretracker


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.userdata.UserGeneral
import com.mabinogifanmade.squiretracker.utils.ConversationUtils
import com.mabinogifanmade.squiretracker.utils.ShrdPrfsUtils
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
    lateinit var squireProgressPreview: HashMap<Int,Int>
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
            squireProgressPreview = UserUtils.getCurrentCharPlayer(context!!).squireProgress
            val progress:Int = UserUtils.getCurrentCharProgressForSquire(context!!,squire.id)
            setSquireText(progress)
            next.setOnClickListener(View.OnClickListener {
                setSquireText(squireProgressPreview.get(squire.id)!!+1)
            })
            previous.setOnClickListener(View.OnClickListener {
                setSquireText(squireProgressPreview.get(squire.id)!!-1)
            })
            nextAccept.setOnClickListener(View.OnClickListener {
                val user: UserGeneral? = ShrdPrfsUtils.getUserData(context!!)
                user?.getCurrentCharacter()?.setSquireProgress(
                    squire,squireProgressPreview.get(squire.id)!!+1!!)
                ShrdPrfsUtils.saveUserData(context!!,user!!)
                YoYo.with(Techniques.FadeInRight)
                    .duration(700)
                    .playOn(sequenceRelative);
                setSquireText(
                    user.getCurrentCharacter().squireProgress.get(squire.id)!!)
            })
        }
    }

    private fun setSquireText(progress: Int) {
        if (context!=null) {
            val index: Int = ConversationUtils.getNumberInSequence(progress, squire.sequenceConvo.length)
            sequenceText.text = context!!.getString(
                R.string.number_sequence,
                (index + 1),
                ConversationUtils.translateCurrentAbv(squire, false, progress)
            )
            squireProgressPreview.put(squire.id, index)
            if (squire.hasHint) {
                hintText.visibility = View.VISIBLE
                hintText.text = context!!.getString(
                    R.string.hint,
                    ConversationUtils.translateCurrentAbv(squire, true, progress)
                )
            } else {
                hintText.visibility = View.GONE
            }
        }
    }
}
