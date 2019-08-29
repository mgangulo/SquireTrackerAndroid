package com.mabinogifanmade.squiretracker


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.utils.ConversationUtils
import kotlinx.android.synthetic.main.fragment_find_seq_hint.*


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

        squireNameTitle.setText(squire.squireName)
        ArrayAdapter<String>(
            context!!,
            android.R.layout.simple_spinner_item,
            getSpinnerList(squire.sequenceHint)
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            hintSpinner1.adapter = it
            hintSpinner2.adapter = it
            hintSpinner3.adapter = it
            hintSpinner4.adapter = it
            hintSpinner5.adapter = it
        }
        ArrayAdapter<String>(
            context!!,
            android.R.layout.simple_spinner_item,
            getSpinnerList(squire.sequenceConvo)
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            seqSpinner1.adapter = it
            seqSpinner2.adapter = it
            seqSpinner3.adapter = it
            seqSpinner4.adapter = it
            seqSpinner5.adapter = it
        }
        setSpinnerListener(hintSpinner1,1)
    }

    private fun setSpinnerListener(spinner: Spinner?, i: Int) {
        spinner?.setOnItemClickListener { parent, view, position, id ->

        }
    }

    private fun getSpinnerList(convoString: String): ArrayList<String> {
        val spinnerList: ArrayList<String> = arrayListOf(getString(R.string.select_text))
        spinnerList.addAll(ConversationUtils.getUniqueConvoList(convoString))
        return spinnerList
    }

    private fun canSearchHint(pos:Int):Boolean{
        return when(pos){
            1  -> hintSpinner1.selectedItemPosition!=0
            2 -> canSearchHint(1) && seqSpinner1.selectedItemPosition!=0
            3 -> canSearchHint(2) && hintSpinner2.selectedItemPosition!=0 && seqSpinner2.selectedItemPosition!=0
            4 -> canSearchHint(3) && hintSpinner3.selectedItemPosition!=0 && seqSpinner3.selectedItemPosition!=0
            5 -> canSearchHint(4) && hintSpinner5.selectedItemPosition!=0 && seqSpinner5.selectedItemPosition!=0
            else -> false
        }
    }

    private fun canSearchSeq(pos:Int):Boolean{
        return when(pos){
            1  -> hintSpinner1.selectedItemPosition!=0 && seqSpinner1.selectedItemPosition!=0
            2 ->  canSearchSeq(1) && hintSpinner2.selectedItemPosition!=0 && seqSpinner2.selectedItemPosition!=0
            3 -> canSearchSeq(2) && hintSpinner3.selectedItemPosition!=0 && seqSpinner3.selectedItemPosition!=0
            4 -> canSearchSeq(3) && hintSpinner4.selectedItemPosition!=0 && seqSpinner4.selectedItemPosition!=0
            5 -> canSearchSeq(5) && hintSpinner5.selectedItemPosition!=0 && seqSpinner5.selectedItemPosition!=0
            else -> false
        }
    }
}
