package com.mabinogifanmade.squiretracker.activitiesfragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.squiredata.SearchResult
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.utils.ConversationUtils
import com.mabinogifanmade.squiretracker.utils.UserUtils
import kotlinx.android.synthetic.main.fragment_find_seq.*

class FindSeqFragment : BaseFragment() {
    val args: FindSeqHintFragmentArgs by navArgs()
    private lateinit var squire: Squire
    private var searchPos:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_seq, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        squire = args.squire
        listener?.updateTitles(getString(R.string.find_squire_progress,squire.squireName))
        squireNameTitle.setText(squire.squireName)
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
        setSpinnerListener(seqSpinner1)
        setSpinnerListener(seqSpinner2)
        setSpinnerListener(seqSpinner3)
        setSpinnerListener(seqSpinner4)
        setSpinnerListener(seqSpinner5)
        resetButton.setOnClickListener {
            if (results.visibility == View.GONE) {
                seqSpinner1.setSelection(0)
                seqSpinner2.setSelection(0)
                seqSpinner3.setSelection(0)
                seqSpinner4.setSelection(0)
                seqSpinner5.setSelection(0)
            }
        }
        saveButton.setOnClickListener {
            if (results.visibility == View.VISIBLE) {
                UserUtils.setCurrentCharSquireProgress(context!!, squire, searchPos)
                findNavController().popBackStack()
            }
        }
    }

    private fun setSpinnerListener(spinner: Spinner?) {
        spinner?.onItemSelectedListener=(object : AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                performSearch()
            }
        })
    }

    private fun performSearch() {
        var pos:Int = 5
        var canSearch = false
        while (pos in 1..5){
            if (canSearchSeq(pos)){
                canSearch = true
                break
            }
            pos = pos.dec()
        }


        if (canSearch) {
            val keySeq: String = getSeqSearchString(pos).replace("-","")
            val result: SearchResult =
                ConversationUtils.searchOnSeq(
                    keySeq,
                    squire.sequenceConvo
                )
            handleSearchResult(result)
        } else {
            setNoMatchesView()
        }
    }

    private fun handleSearchResult(result: SearchResult) {
        if (result.matches == 1) {
            searchPos = result.seqPos!!.dec()
            searchResults.visibility = View.GONE
            suggestions.visibility = View.GONE
            resetButton.visibility = View.GONE
            results.setText(getString(R.string.found_match, result.seqPos.toString()))
            results.visibility = View.VISIBLE
            saveButton.visibility = View.VISIBLE

        } else if (result.matches > 1) {
            searchPos = 0
            results.visibility = View.GONE
            saveButton.visibility = View.GONE
            searchResults.setText(getString(R.string.found_number_matches, result.matches.toString()))
            suggestions.setText(getString(R.string.try_these, result.suggest))
            searchResults.visibility = View.VISIBLE
            suggestions.visibility = View.VISIBLE
            resetButton.visibility = View.VISIBLE
        } else {
            setNoMatchesView()
        }
    }

    private fun setNoMatchesView() {
        searchPos = 0
        saveButton.visibility = View.GONE
        suggestions.visibility = View.GONE
        results.visibility = View.GONE
        searchResults.setText(getString(R.string.no_matches_found))
        searchResults.visibility = View.VISIBLE
        resetButton.visibility = View.VISIBLE
    }

    private fun getSpinnerList(convoString: String): ArrayList<String> {
        val spinnerList: ArrayList<String> = arrayListOf(getString(R.string.select_text))
        spinnerList.addAll(ConversationUtils.getUniqueConvoList(convoString))
        return spinnerList
    }

    private fun canSearchSeq(pos: Int): Boolean {
        return when (pos) {
            1 -> seqSpinner1.selectedItemPosition != 0
            2 -> canSearchSeq(1) && seqSpinner2.selectedItemPosition != 0
            3 -> canSearchSeq(2) && seqSpinner3.selectedItemPosition != 0
            4 -> canSearchSeq(3) && seqSpinner4.selectedItemPosition != 0
            5 -> canSearchSeq(4) && seqSpinner5.selectedItemPosition != 0
            else -> false
        }
    }

    private fun getSeqSearchString(pos: Int): String {
        val stringBuilder: StringBuilder = StringBuilder()
        return when (pos) {
            1 -> stringBuilder.append(ConversationUtils.translateOption(seqSpinner1.selectedItem.toString())).toString()
            2 -> stringBuilder.append(getSeqSearchString(1))
                .append(ConversationUtils.translateOption(seqSpinner2.selectedItem.toString())).toString()
            3 -> stringBuilder.append(getSeqSearchString(2))
                .append(ConversationUtils.translateOption(seqSpinner3.selectedItem.toString())).toString()
            4 -> stringBuilder.append(getSeqSearchString(3))
                .append(ConversationUtils.translateOption(seqSpinner4.selectedItem.toString())).toString()
            5 -> stringBuilder.append(getSeqSearchString(4))
                .append(ConversationUtils.translateOption(seqSpinner5.selectedItem.toString())).toString()
            else -> ""
        }
    }
    private fun getSeqSpinner(pos: Int): Spinner? {
        return when (pos) {
            1 -> seqSpinner1
            2 -> seqSpinner2
            3 -> seqSpinner3
            4 -> seqSpinner4
            5 -> seqSpinner5
            else -> null
        }
    }
}
