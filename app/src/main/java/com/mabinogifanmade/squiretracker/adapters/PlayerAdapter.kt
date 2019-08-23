package com.mabinogifanmade.squiretracker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.userdata.PlayerChar

class PlayerAdapter(val playerChars: ArrayList<PlayerChar>,val currentChar:PlayerChar, val context: Context)
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
        holder.switchButton.visibility = when (character.equals(currentChar)){
            true -> View.INVISIBLE
            else -> View.VISIBLE
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val editButton:ImageButton = itemView.findViewById(R.id.editButton)
        val deleteButton:ImageButton = itemView.findViewById(R.id.deleteButton)
        val switchButton: AppCompatButton = itemView.findViewById(R.id.switchButton)
        val characterName: TextView = itemView.findViewById(R.id.charName)
        val characterServer: TextView = itemView.findViewById(R.id.charServer)
        val charAvatar: ImageView = itemView.findViewById(R.id.characterAvatar)

        /*init{

        }*/
    }

}