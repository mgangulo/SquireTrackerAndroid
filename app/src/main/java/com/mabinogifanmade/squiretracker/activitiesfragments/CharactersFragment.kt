package com.mabinogifanmade.squiretracker.activitiesfragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.adapters.PlayerAdapter
import com.mabinogifanmade.squiretracker.userdata.UserGeneral
import com.mabinogifanmade.squiretracker.utils.ShrdPrfsUtils
import kotlinx.android.synthetic.main.fragment_characters.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CharactersFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (context != null) {
            val user: UserGeneral = ShrdPrfsUtils.getUserData(context!!)!!
            val adapter: PlayerAdapter = PlayerAdapter(
                user.playerChars,
                context!!
            )
            listener?.updateTitles(getString(R.string.characters))
            charRecycler.adapter = adapter
            setTouchHelper()
            addChar.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_nav_characters_to_newPlayerCharFragment, null))
        }
    }

    private fun setTouchHelper() {
        val callback:ItemTouchHelper.Callback = object :
            ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN ,0){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                if (charRecycler.adapter is PlayerAdapter){
                    (charRecycler.adapter as PlayerAdapter).swapItems(
                        viewHolder.getAdapterPosition(),
                        target.getAdapterPosition()
                    )
                }
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }

            override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
                super.clearView(recyclerView, viewHolder)
                if (charRecycler.adapter is PlayerAdapter) {
                    (charRecycler.adapter as PlayerAdapter).finishDragAndDrop()
                }
            }

            override fun interpolateOutOfBoundsScroll(
                recyclerView: RecyclerView,
                viewSize: Int,
                viewSizeOutOfBounds: Int,
                totalSize: Int,
                msSinceStartScroll: Long
            ): Int {
                val direction = Math.signum(viewSizeOutOfBounds.toFloat()).toInt()
                return 15 * direction
            }

        }
        val touchHelper:ItemTouchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(charRecycler)
    }
}
