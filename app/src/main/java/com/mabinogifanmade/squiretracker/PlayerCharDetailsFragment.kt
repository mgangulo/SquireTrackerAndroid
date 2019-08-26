package com.mabinogifanmade.squiretracker


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mabinogifanmade.squiretracker.userdata.PlayerChar
import com.mabinogifanmade.squiretracker.userdata.UserGeneral
import com.mabinogifanmade.squiretracker.utils.GeneralUtils
import com.mabinogifanmade.squiretracker.utils.ShrdPrfsUtils
import kotlinx.android.synthetic.main.fragment_char_details.*
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
            var playerChar: PlayerChar? = null
            if (editMode) {
                val user: UserGeneral? = ShrdPrfsUtils.getUserData(context!!)
                playerChar = user?.playerChars?.get(charPos)
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
            val user: UserGeneral? = ShrdPrfsUtils.getUserData(context!!)
            user?.playerChars?.get(charPos)?.charName = playerName
            user?.playerChars?.get(charPos)?.server = server
            ShrdPrfsUtils.saveUserData(context!!, user!!)
            findNavController().popBackStack()
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
        val daiProgressString = squireProgress.daiProgressEdit.getText().toString()
        val eirlysProgressString = squireProgress.eirlysProgressEdir.getText().toString()
        val elsieProgressString = squireProgress.elsieProgressEdit.getText().toString()
        val kaourProgressString = squireProgress.kaourProgressEdit.getText().toString()

        val newPlayer = PlayerChar(
            playerName, server,
            GeneralUtils.textToNumber(daiProgressString),
            GeneralUtils.textToNumber(eirlysProgressString),
            GeneralUtils.textToNumber(elsieProgressString),
            GeneralUtils.textToNumber(kaourProgressString)
        )
        if (context != null) {
            val user: UserGeneral? = ShrdPrfsUtils.getUserData(context!!)
            user?.playerChars?.add(newPlayer)
            ShrdPrfsUtils.saveUserData(context!!, user!!)
            findNavController().popBackStack()
        }
    }
}
