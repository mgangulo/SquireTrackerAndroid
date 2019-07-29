package com.mabinogifanmade.squiretracker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.utils.ConversationUtils

class MiniSquireAdapter(val squireList: ArrayList<Squire>, val context: Context, var isGrid: Boolean) :
    RecyclerView.Adapter<MiniViewHolder>() {
    val GRID_TYPE: Int = 1;
    val LIST_TYPE: Int = 2;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiniViewHolder {
        val id: Int = when (viewType) {
            GRID_TYPE -> R.layout.grid_squire_item
            LIST_TYPE -> R.layout.mini_squire_item
            else -> R.layout.grid_squire_item
        }
        return MiniViewHolder(
            LayoutInflater.from(parent.context).inflate(
                id,
                parent,
                false
            )
        );
    }

    override fun getItemViewType(position: Int): Int {
        if (isGrid)
            return GRID_TYPE
        else
            return LIST_TYPE
    }

    override fun getItemCount(): Int {
        return squireList.size
    }

    override fun onBindViewHolder(holder: MiniViewHolder, position: Int) {
        val squire: Squire = squireList[position]
        holder.nameSquire.text = squire.squireName
        holder.sequence.text = context.getString(
            R.string.number_sequence,
            1, ConversationUtils.translateAbv(squire.sequenceConvo[0])
        )
        if (squire.hasHint) {
            holder.hint.visibility = View.VISIBLE
            holder.hint.text = context.getString(
                R.string.hint,
                ConversationUtils.translateAbv(squire.sequenceHint[0])
            )
        } else {
            holder.hint.visibility = View.GONE
        }
    }

    fun setViewType(isGrid: Boolean) {
        this.isGrid = isGrid
        notifyDataSetChanged()
    }

}


class MiniViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.squireImage);
    val nameSquire: TextView = itemView.findViewById(R.id.squireName);
    val sequence: TextView = itemView.findViewById(R.id.sequenceText);
    val hint: TextView = itemView.findViewById(R.id.hintText);

}
