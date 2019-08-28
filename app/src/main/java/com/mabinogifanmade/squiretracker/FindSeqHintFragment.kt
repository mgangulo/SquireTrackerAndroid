package com.mabinogifanmade.squiretracker


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.utils.ConversationUtils
import java.util.stream.Collectors


/**
 * A simple [Fragment] subclass.
 *
 */
class FindSeqHintFragment : Fragment() {
    val args: FindSeqHintFragmentArgs by navArgs()
    private lateinit var squire: Squire

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_seq_hint, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        squire = args.squire
        val hintSpinnerList: ArrayList<String> = arrayListOf(getString(R.string.select_text))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            hintSpinnerList.addAll(squire.sequenceHint
                .chars().distinct()
                .mapToObj({ c -> ConversationUtils.translateAbv(c as Char) })
                .collect(Collectors.toList()))
        }
    }
}
