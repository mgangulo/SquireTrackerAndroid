package com.mabinogifanmade.squiretracker.activitiesfragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.GridLayoutManager
import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.adapters.SquireAdapter
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.userdata.PlayerChar
import com.mabinogifanmade.squiretracker.userdata.UserGeneral
import com.mabinogifanmade.squiretracker.utils.ShrdPrfsUtils
import kotlinx.android.synthetic.main.fragment_main.*
import me.rishabhkhanna.customtogglebutton.CustomToggleButton
import java.util.*

class MainFragment : BaseFragment() {
    private var isGrid: Boolean = true
    private var mainListener: OnMainFragmentListener? = null
    private lateinit var squireList: ArrayList<Squire>
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
        squireList = arrayListOf(Squire.DAI, Squire.EIRLYS, Squire.ELSIE, Squire.KAOUR)
        listOption.setOnClickListener { changeViews(true)}
        toggleDai?.setOnCheckedChangeListener(getSquireToggleListener(Squire.DAI))
        toggleEirlys?.setOnCheckedChangeListener(getSquireToggleListener(Squire.EIRLYS))
        toggleElsie?.setOnCheckedChangeListener(getSquireToggleListener(Squire.ELSIE))
        toggleKaour?.setOnCheckedChangeListener(getSquireToggleListener(Squire.KAOUR))
        user = ShrdPrfsUtils.getUserData(context!!)
        squireRecyclerView?.adapter = SquireAdapter(squireList, context!!, user!!.prefersGrid,
            user!!.getCurrentCharacter().squireProgress)


    }

    private fun changeViews(save:Boolean) {
        if (context!=null) {
            if (save)
                isGrid = !isGrid
            listOption.setImageResource(
                when(isGrid){
                    true -> R.drawable.selector_list
                    else -> R.drawable.selector_grid
                }
            )
            setRecyclerViewType(isGrid,save)
        }
    }

    override fun onResume() {
        super.onResume()
        setUserDataOnView()
    }

    fun setUserDataOnView() {
        if (user != null) {
            val currentChar: PlayerChar = user!!.getCurrentCharacter()
            isGrid = user!!.prefersGrid
            changeViews(false)
            var itChanged = false
            for (i in (squireList.size - 1) downTo 0) {
                val squire: Squire = squireList.get(i)
                if (!currentChar.squiresActive.contains(squire.id)) {
                    squireList.remove(squire)
                    itChanged = true
                }
                Log.v("SquireList",squire.name+" check: "+currentChar.squiresActive.contains(squire.id).toString())
                // getToggleSquire(squire)?.setChecked(currentChar.squiresActive.contains(squire.id))
                //getToggleSquire(squire)?.setSelected(currentChar.squiresActive.contains(squire.id))
                if (getToggleSquire(squire).isChecked!=currentChar.squiresActive.contains(squire.id))
                    getToggleSquire(squire)?.toggle()
                getToggleSquire(squire)?.rootView?.refreshDrawableState()
                getToggleSquire(squire)?.rootView?.requestLayout()
                getToggleSquire(squire)?.rootView?.forceLayout()
            }
            if (itChanged){
                squireRecyclerView?.adapter?.notifyDataSetChanged()
            }
            mainListener?.updateInfoOnNav()
            listener?.updateTitles(getString(R.string.app_name),getString(R.string.character_progress,currentChar.charName))
        }
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMainFragmentListener) {
            mainListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mainListener = null
    }

    interface OnMainFragmentListener {
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

    private fun setRecyclerViewType(isGrid: Boolean, save:Boolean) {
        val layoutManager = squireRecyclerView?.layoutManager as GridLayoutManager
        layoutManager?.setSpanCount(
            when (isGrid) {
                true -> 2
                false -> 1
            }
        )
        (squireRecyclerView?.adapter as SquireAdapter).setViewType(isGrid)
        if (save) {
            val user: UserGeneral? = ShrdPrfsUtils.getUserData(context!!)
            user?.prefersGrid = isGrid
            ShrdPrfsUtils.saveUserData(context!!, user!!)
        }
    }

    private fun getToggleSquire(squire:Squire): CustomToggleButton {
        return when (squire){
            Squire.DAI ->  toggleDai
            Squire.EIRLYS -> toggleEirlys
            Squire.ELSIE -> toggleElsie
            else -> toggleKaour
        }
    }
}
