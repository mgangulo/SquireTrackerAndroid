package com.mabinogifanmade.squiretracker


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputLayout
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.userdata.PlayerChar
import com.mabinogifanmade.squiretracker.utils.GeneralUtils
import com.mabinogifanmade.squiretracker.utils.UserUtils
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

        val daiProgress = GeneralUtils.textToProgress(squireProgress.daiProgressEdit.getText().toString())
        val eirlysProgress = GeneralUtils.textToProgress(squireProgress.eirlysProgressEdit.getText().toString())
        val elsieProgress = GeneralUtils.textToProgress(squireProgress.elsieProgressEdit.getText().toString())
        val kaourProgress = GeneralUtils.textToProgress(squireProgress.kaourProgressEdit.getText().toString())
        if (checkValidSquireProgress(daiProgress, squireProgress.daiProgressLayout, Squire.DAI) and
            checkValidSquireProgress(eirlysProgress, squireProgress.eirlysProgressLayout, Squire.EIRLYS) and
            checkValidSquireProgress(elsieProgress, squireProgress.elsieProgressLayout, Squire.ELSIE) and
            checkValidSquireProgress(kaourProgress, squireProgress.kaourProgressLayout, Squire.KAOUR)
        ) {
            val newPlayer = PlayerChar(
                playerName, server,
                daiProgress - 1,
                eirlysProgress - 1,
                elsieProgress - 1,
                kaourProgress - 1
            )
            if (context != null) {
                if (!UserUtils.characterExist(context!!, newPlayer)) {
                    UserUtils.saveNewPlayer(context!!, newPlayer)
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(context!!, R.string.character_exist_error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun checkValidSquireProgress(progress: Int, layout: TextInputLayout, squire: Squire): Boolean {
        if (progress >= 1 && progress <= squire.sequenceConvo.length) {
            layout.setError(null)
            return true
        } else {
            layout.setError(getString(R.string.progress_error_msg, squire.sequenceConvo.length))
            return false
        }
    }
}
