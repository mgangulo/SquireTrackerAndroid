package com.mabinogifanmade.squiretracker.adapters

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.activitiesfragments.CharactersFragmentDirections
import com.mabinogifanmade.squiretracker.userdata.PlayerChar
import com.mabinogifanmade.squiretracker.utils.DialogUtils
import com.mabinogifanmade.squiretracker.utils.UserUtils

class PlayerAdapter(val playerChars: ArrayList<PlayerChar>, val context: Context)
    : RecyclerView.Adapter<PlayerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.player_character_item,parent,false))
    }

    override fun getItemCount(): Int {
        return playerChars.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character: PlayerChar = playerChars.get(position)
        holder.characterName.setText(character.charName)
        holder.characterServer.setText(character.server)
        holder.switchButton.visibility = when (position == UserUtils.getCurrentCharPlayerPos(context)){
            true -> View.INVISIBLE
            else -> View.VISIBLE
        }
        holder.deleteButton.visibility = when (playerChars.size<=1){
            true -> View.INVISIBLE
            else -> View.VISIBLE
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val editButton:ImageButton = itemView.findViewById(R.id.editButton)
        val deleteButton:ImageButton = itemView.findViewById(R.id.deleteButton)
        val switchButton: AppCompatButton = itemView.findViewById(R.id.switchButton)
        val characterName: TextView = itemView.findViewById(R.id.charName)
        val characterServer: TextView = itemView.findViewById(R.id.charServer)
        val charAvatar: ImageView = itemView.findViewById(R.id.characterAvatar)

        init{
            editButton.setOnClickListener {
                val action =
                CharactersFragmentDirections.actionNavCharactersToNewPlayerCharFragment(true,adapterPosition)
                Navigation.findNavController(it).navigate(action)
            }
            deleteButton.setOnClickListener {
                if (playerChars.size>1) {
                    DialogUtils.showDialog(it.context,
                        it.context.getString(R.string.are_you_sure),
                        it.context.getString(R.string.delete_char_message, playerChars.get(adapterPosition).charName),
                        it.context.getString(R.string.yes_button),
                        DialogInterface.OnClickListener { dialog, which ->
                            if (adapterPosition >= 0 && adapterPosition < playerChars.size) {
                                UserUtils.removePlayerAt(it.context,adapterPosition)
                                playerChars.removeAt(adapterPosition)
                                notifyDataSetChanged()
                            }
                            dialog.dismiss()
                        },
                        it.context.getString(R.string.no_button),
                        DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                } else{
                    Toast.makeText(it.context,R.string.delete_with_one_char,Toast.LENGTH_LONG).show()
                }
            }
            switchButton.setOnClickListener {
                UserUtils.switchPlayerCharacter(it.context,adapterPosition)
                notifyDataSetChanged()
            }
        }
    }

}