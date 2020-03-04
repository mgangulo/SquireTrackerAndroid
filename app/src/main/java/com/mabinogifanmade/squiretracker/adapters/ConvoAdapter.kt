package com.mabinogifanmade.squiretracker.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.utils.ConversationUtils

class ConvoAdapter(
    val context: Context,
    val squire: Squire,
    val currentProgress: Int
) :
    RecyclerView.Adapter<ConvoAdapter.ViewHolder>() {
    private val DATA_VIEW_TYPE: Int = R.layout.convo_item
    private val DATA_NO_HINT_VIEW_TYPE: Int = R.layout.convo_item_no_hint
    private val HEADER_VIEW_TYPE: Int = R.layout.level_squire_item


    /*private fun transformHashToArray(dataSquire: HashMap<Int, ArrayList<SpecialOption>>): ArrayList<Any> {
        val list: ArrayList<Any> = ArrayList()
        for (i in dataSquire.keys.sorted()) {
            list.add(i)
            headerPos.add(list.size - 1)
            size = size.inc()
            list.add(arrayOf(context!!.getString(R.string.percent_text),
                context!!.getString(R.string.hint_text),
                context!!.getString(R.string.word_text)))
            size = size.inc()
            for (option in dataSquire.get(i)!!.iterator()) {
                list.add(option)
                size = size.inc()
            }
        }
        return list
    }*/


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConvoAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                viewType,
                parent,
                false
            )
        );
    }

    override fun getItemCount(): Int {
        return squire.sequenceConvo.length + 2
    }

    override fun onBindViewHolder(holder: ConvoAdapter.ViewHolder, position: Int) {

        if (position == 0) {
            holder.levelTitle?.setText(squire.squireName)
        } else if (position == 1) {
            holder.numberText?.setText(context.getString(R.string.sequence_text))
            holder.hintText?.setText(context.getString(R.string.hint_text))
            holder.sequenceText?.setText(context.getString(R.string.word_text))
            holder.numberText?.setTypeface(holder.numberText.getTypeface(), Typeface.BOLD)
            holder.hintText?.setTypeface(holder.hintText.getTypeface(), Typeface.BOLD)
            holder.sequenceText?.setTypeface(holder.sequenceText.getTypeface(), Typeface.BOLD)
            holder.rootLayout?.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        } else {
            val pos: Int = position - 2;
            holder.numberText?.setText((pos + 1).toString())
            holder.hintText?.setText(
                ConversationUtils.translateAbv(squire.sequenceHint[pos])
            )
            holder.sequenceText?.setText(
                ConversationUtils.translateAbv(squire.sequenceConvo[pos])
            )
            holder.numberText?.setTypeface(null, Typeface.NORMAL)
            holder.hintText?.setTypeface(null, Typeface.NORMAL)
            holder.sequenceText?.setTypeface(null, Typeface.NORMAL)
            setBackgroundColor(holder, pos)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position != 0) {
            return when (squire.hasHint) {
                false -> DATA_NO_HINT_VIEW_TYPE
                else -> DATA_VIEW_TYPE
            }
        }
        return HEADER_VIEW_TYPE

    }

    private fun setBackgroundColor(holder: ViewHolder, position: Int) {
        if (position <                              currentProgress) {
            holder.rootLayout?.setBackgroundColor(ContextCompat.getColor(context, R.color.super_light_green))
        } else {
            holder.rootLayout?.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val levelTitle: AppCompatTextView? = itemView.findViewById(R.id.levelTitle)
        val numberText: AppCompatTextView? = itemView.findViewById(R.id.numberText)
        val hintText: AppCompatTextView? = itemView.findViewById(R.id.hintText)
        val sequenceText: AppCompatTextView? = itemView.findViewById(R.id.sequenceText)
        val rootLayout: ConstraintLayout? = itemView.findViewById(R.id.rootLayout)
    }

}