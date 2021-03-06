package com.mabinogifanmade.squiretracker.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.squiredata.SpecialOption

class SpecialConvoAdapter(
    val context: Context,
    dataHash: HashMap<Int, ArrayList<SpecialOption>>,
    val hasHint: Boolean
) :
    RecyclerView.Adapter<SpecialConvoAdapter.ViewHolder>() {
    private var size: Int = 0
    private val headerPos: ArrayList<Int> = ArrayList()

    private val DATA_VIEW_TYPE:Int = R.layout.convo_item
    private val DATA_NO_HINT_VIEW_TYPE:Int = R.layout.convo_item_no_hint
    private val HEADER_VIEW_TYPE:Int = R.layout.level_squire_item

    private val convoList: ArrayList<Any> = transformHashToArray(dataHash)

    private fun transformHashToArray(dataSquire: HashMap<Int, ArrayList<SpecialOption>>): ArrayList<Any> {
        val list: ArrayList<Any> = ArrayList()
        for (i in dataSquire.keys.sorted()) {
            list.add(i)
            headerPos.add(list.size - 1)
            size = size.inc()
            list.add(arrayOf(context.getString(R.string.percent_text),
                context.getString(R.string.hint_text),
                context.getString(R.string.word_text)))
            size = size.inc()
            for (option in dataSquire.get(i)!!.iterator()) {
                list.add(option)
                size = size.inc()
            }
        }
        return list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialConvoAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                viewType,
                parent,
                false
            )
        );
    }

    override fun getItemCount(): Int {
        return when (size < 0) {
            true -> 0
            else -> size
        }
    }

    override fun onBindViewHolder(holder: SpecialConvoAdapter.ViewHolder, position: Int) {
        val data:Any = convoList[position]
        if (data is Int){
            holder.levelTitle?.setText(context.getString(R.string.level_text,data.toString()))
        }else if (data is Array<*>) {
            holder.percentText?.setText(data[0].toString())
            holder.hintText?.setText(data[1].toString())
            holder.sequenceText?.setText(data[2].toString())
            holder.percentText?.setTypeface(holder.percentText.getTypeface(), Typeface.BOLD)
            holder.hintText?.setTypeface(holder.hintText.getTypeface(), Typeface.BOLD)
            holder.sequenceText?.setTypeface(holder.sequenceText.getTypeface(), Typeface.BOLD)
        } else if (data is SpecialOption){
            holder.percentText?.setText(data.percent.toString())
            holder.hintText?.setText(data.hint)
            holder.sequenceText?.setText(data.convoText)
            holder.percentText?.setTypeface(null, Typeface.NORMAL)
            holder.hintText?.setTypeface(null, Typeface.NORMAL)
            holder.sequenceText?.setTypeface(null, Typeface.NORMAL)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (!(position in headerPos)){
            return when (hasHint){
                false -> DATA_NO_HINT_VIEW_TYPE
                else -> DATA_VIEW_TYPE
            }
        }
        return HEADER_VIEW_TYPE

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val levelTitle:AppCompatTextView? = itemView.findViewById(R.id.levelTitle)
        val percentText:AppCompatTextView? = itemView.findViewById(R.id.numberText)
        val hintText: AppCompatTextView? = itemView.findViewById(R.id.hintText)
        val sequenceText: AppCompatTextView? = itemView.findViewById(R.id.sequenceText)
    }

}