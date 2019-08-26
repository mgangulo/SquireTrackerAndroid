package com.mabinogifanmade.squiretracker

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.mabinogifanmade.squiretracker.adapters.SquireAdapter
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.userdata.PlayerChar
import com.mabinogifanmade.squiretracker.userdata.UserGeneral
import com.mabinogifanmade.squiretracker.utils.ShrdPrfsUtils
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*

class MainFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private val squireList: ArrayList<Squire> = arrayListOf(Squire.DAI, Squire.EIRLYS, Squire.ELSIE, Squire.KAOUR)
    private var user: UserGeneral? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listOption.setOnCheckedChangeListener { group, checkedId ->
            setRecyclerViewType(
                when (checkedId) {
                    R.id.gridRadioOption -> true
                    else -> false
                }
            )
        }

        toggleDai?.setOnCheckedChangeListener(getSquireToggleListener(Squire.DAI))
        toggleEirlys?.setOnCheckedChangeListener(getSquireToggleListener(Squire.EIRLYS))
        toggleElsie?.setOnCheckedChangeListener(getSquireToggleListener(Squire.ELSIE))
        toggleKaour?.setOnCheckedChangeListener(getSquireToggleListener(Squire.KAOUR))

        user = ShrdPrfsUtils.getUserData(context!!)
        squireRecyclerView?.adapter = SquireAdapter(squireList, context!!, false,
            user!!.getCurrentCharacter().squireProgress)
        setUserDataOnView()
    }

    fun setUserDataOnView() {
        if (user != null) {

            val currentChar: PlayerChar = user!!.getCurrentCharacter()
            gridRadioOption.isChecked = user!!.prefersGrid
            listRadioOption.isChecked = !user!!.prefersGrid
            for (i in (squireList.size - 1) downTo 0) {
                val squire: Squire = squireList.get(i)
                if (!currentChar.squiresActive.contains(squire.id)) {
                    squireList.remove(squire)
                    if (squire.equals(Squire.DAI)) {
                        toggleDai?.isChecked = false
                    }
                    if (squire.equals(Squire.EIRLYS)) {
                        toggleEirlys?.isChecked = false
                    }
                    if (squire.equals(Squire.ELSIE)) {
                        toggleElsie?.isChecked = false
                    }
                    if (squire.equals(Squire.KAOUR)) {
                        toggleKaour?.isChecked = false
                    }
                }
            }
            listener?.updateInfoOnNav()
        }
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun updateInfoOnNav()
    }

    private fun getSquireToggleListener(squire: Squire): CompoundButton.OnCheckedChangeListener {
        return CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isPressed) {
                val user: UserGeneral = ShrdPrfsUtils.getUserData(context!!)!!
                if (isChecked) {
                    if (!squireList.contains(squire)) {
                        squireList.add(squire)
                        user.getCurrentCharacter().squiresActive.add(squire.id)
                        Collections.sort(squireList)
                        squireRecyclerView?.adapter?.notifyDataSetChanged()
                    }
                } else {
                    if (squireList.contains(squire)) {
                        squireList.remove(squire)
                        user.getCurrentCharacter().squiresActive.remove(squire.id)
                        squireRecyclerView?.adapter?.notifyDataSetChanged()
                    }
                }
                ShrdPrfsUtils.saveUserData(buttonView.context, user)
            }
        }
    }

    private fun setRecyclerViewType(isGrid: Boolean) {
        val layoutManager = squireRecyclerView?.layoutManager as GridLayoutManager
        layoutManager?.setSpanCount(
            when (isGrid) {
                true -> 2
                false -> 1
            }
        )
        (squireRecyclerView?.adapter as SquireAdapter).setViewType(isGrid)
        val user:UserGeneral? = ShrdPrfsUtils.getUserData(context!!)
        user?.prefersGrid = isGrid
        ShrdPrfsUtils.saveUserData(context!!,user!!)
    }
}
