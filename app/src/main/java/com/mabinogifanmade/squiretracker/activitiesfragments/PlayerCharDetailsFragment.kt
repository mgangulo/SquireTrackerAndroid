package com.mabinogifanmade.squiretracker.activitiesfragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputLayout
import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.userdata.PlayerChar
import com.mabinogifanmade.squiretracker.utils.ConversationUtils
import com.mabinogifanmade.squiretracker.utils.GeneralUtils
import com.mabinogifanmade.squiretracker.utils.UserUtils
import kotlinx.android.synthetic.main.fragment_char_details.*
import kotlinx.android.synthetic.main.include_player_info.*
import kotlinx.android.synthetic.main.include_player_info.view.*
import kotlinx.android.synthetic.main.include_squire_progress.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class PlayerCharDetailsFragment : Fragment() {
    val args: PlayerCharDetailsFragmentArgs by navArgs()
    var editMode: Boolean = false
    var charPos: Int = -1
    var onBoardingMode: Boolean = false
    var avyPos = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_char_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (context != null) {
            editMode = args.isEditMode
            charPos = args.charPos
            onBoardingMode = args.isOnBoardingMode
            var playerChar: PlayerChar? = null
            if (editMode) {
                playerChar = UserUtils.getCharPlayerAt(context!!, charPos)
            }
            ArrayAdapter.createFromResource(
                context!!,
                R.array.servers_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                playerInfo.serverSpinner.adapter = adapter
                if (editMode) {
                    playerInfo.serverSpinner.setSelection(
                        when (playerChar!!.server) {
                            "Alexina" -> 0
                            else -> 1
                        }
                    )
                }
            }
            avatar1.setOnClickListener { setSelected(1) }
            avatar2.setOnClickListener { setSelected(2) }
            avatar3.setOnClickListener { setSelected(3) }
            avatar4.setOnClickListener { setSelected(4) }
            avatar5.setOnClickListener { setSelected(5) }
            avatar6.setOnClickListener { setSelected(6) }
            avatar7.setOnClickListener { setSelected(7) }
            avatar8.setOnClickListener { setSelected(8) }
            if (editMode) {
                playerInfo.charNameEdit.setText(playerChar!!.charName)
                squireProgress.visibility = View.GONE
            }
            saveButton.setOnClickListener {
                if (!editMode)
                    saveNew()
                else
                    saveEdit()
            }
        }
    }

    private fun saveEdit() {
        val playerName: String = playerInfo.charNameEdit.getText().toString()
        if (playerName.isNullOrEmpty()) {
            playerInfo.charNameInputLayout.setError(getString(R.string.name_empty_error))
            return
        } else {
            playerInfo.charNameInputLayout.setError(null)
        }
        val server: String = playerInfo.serverSpinner.selectedItem.toString()
        if (context != null) {
            if (!UserUtils.characterExist(context!!, charPos, playerName, server)) {
                UserUtils.editPlayerAt(context!!, charPos, playerName, server)
                findNavController().popBackStack()
            } else {
                Toast.makeText(context!!, R.string.character_exist_error, Toast.LENGTH_LONG).show()
            }
        }
    }


    fun saveNew() {
        val playerName: String = playerInfo.charNameEdit.getText().toString()
        if (playerName.isNullOrEmpty()) {
            playerInfo.charNameInputLayout.setError(getString(R.string.name_empty_error))
            return
        } else {
            playerInfo.charNameInputLayout.setError(null)
        }
        val server: String = playerInfo.serverSpinner.selectedItem.toString()

        val daiProgress =
            GeneralUtils.textToProgress(squireProgress.daiProgressEdit.getText().toString())
        val eirlysProgress =
            GeneralUtils.textToProgress(squireProgress.eirlysProgressEdit.getText().toString())
        val elsieProgress =
            GeneralUtils.textToProgress(squireProgress.elsieProgressEdit.getText().toString())
        val kaourProgress =
            GeneralUtils.textToProgress(squireProgress.kaourProgressEdit.getText().toString())
        if (checkValidSquireProgress(
                daiProgress,
                squireProgress.daiProgressLayout
            ) and
            checkValidSquireProgress(
                eirlysProgress,
                squireProgress.eirlysProgressLayout
            ) and
            checkValidSquireProgress(
                elsieProgress,
                squireProgress.elsieProgressLayout
            ) and
            checkValidSquireProgress(
                kaourProgress,
                squireProgress.kaourProgressLayout
            )
        ) {
            val newPlayer = PlayerChar(
                playerName, server,
                ConversationUtils.getNumberInSequenceWithOffset(
                    daiProgress - 1,
                    Squire.DAI.sequenceConvo.length
                ),
                ConversationUtils.getNumberInSequenceWithOffset(
                    eirlysProgress - 1,
                    Squire.EIRLYS.sequenceConvo.length
                ),
                ConversationUtils.getNumberInSequenceWithOffset(
                    elsieProgress - 1,
                    Squire.ELSIE.sequenceConvo.length
                ),
                ConversationUtils.getNumberInSequenceWithOffset(
                    kaourProgress - 1,
                    Squire.KAOUR.sequenceConvo.length
                ),
                getAvatarFromPos(avyPos)
            )
            if (context != null) {
                if (!onBoardingMode) {
                    if (!UserUtils.characterExist(context!!, newPlayer)) {
                        UserUtils.saveNewPlayer(context!!, newPlayer)
                        findNavController().popBackStack()
                    } else {
                        Toast.makeText(context!!, R.string.character_exist_error, Toast.LENGTH_LONG)
                            .show()
                    }
                } else {
                    try {
                        UserUtils.saveNewPlayerOnBoarding(context!!, newPlayer)
                        val directions = PlayerCharDetailsFragmentDirections.navigateToMain()
                        findNavController().navigate(
                            directions,
                            NavOptions.Builder()
                                .setPopUpTo(
                                    R.id.onBoardingNewPlayerFragment,
                                    true
                                ).build()
                        )
                        activity!!.finish()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    fun checkValidSquireProgress(progress: Int, layout: TextInputLayout): Boolean {
        if (progress >= 1) {
            layout.setError(null)
            return true
        } else {
            layout.setError(getString(R.string.progress_error_msg))
            return false
        }
    }

    fun getAvatarFromPos(pos: Int): Int {
        return when (pos) {
            1 -> R.drawable.ic_mabi_orange
            2 -> R.drawable.ic_mabi_yellow
            3 -> R.drawable.ic_mabi_green
            4 -> R.drawable.ic_mabi_blue
            5 -> R.drawable.ic_mabi_purple
            6 -> R.drawable.ic_mabi_pink
            7 -> R.drawable.ic_mabi_red
            8 -> R.drawable.ic_mabi_black
            else -> R.drawable.ic_mabi_orange
        }
    }

    fun getImageViewFromPos(pos: Int): ImageView {
        return when (pos) {
            1 -> avatar1
            2 -> avatar2
            3 -> avatar3
            4 -> avatar4
            5 -> avatar5
            6 -> avatar6
            7 -> avatar7
            8 -> avatar8
            else -> avatar1
        }
    }

    fun setSelected(pos:Int){
        getImageViewFromPos(avyPos).setBackgroundResource(0)
        avyPos = pos
        getImageViewFromPos(avyPos).setBackgroundResource(R.drawable.avatar_border)
    }
}
