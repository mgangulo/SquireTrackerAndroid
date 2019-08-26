package com.mabinogifanmade.squiretracker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.mabinogifanmade.squiretracker.MainFragmentDirections
import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.userdata.UserGeneral
import com.mabinogifanmade.squiretracker.utils.ConversationUtils
import com.mabinogifanmade.squiretracker.utils.ShrdPrfsUtils

class SquireAdapter(val squireList: ArrayList<Squire>, val context: Context, var isGrid: Boolean,
                    val squireProgressPreview: HashMap<Int, Int>) :
    RecyclerView.Adapter<SquireAdapter.MiniViewHolder>() {
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
        setSquireText(holder,squire,squireProgressPreview.get(squire.id)!!)
    }

    private fun setSquireText(holder: SquireAdapter.MiniViewHolder, squire: Squire, progress:Int) {
        val index:Int = ConversationUtils.getNumberInSequence(progress,squire.sequenceConvo.length)
        holder.sequence.text = context.getString(
            R.string.number_sequence,
            (index+1),
            ConversationUtils.translateCurrentAbv(squire,false,progress)
        )
        squireProgressPreview.put(squire.id,index)
        if (squire.hasHint) {
            holder.hint.visibility = View.VISIBLE
            holder.hint.text = context.getString(
                R.string.hint,
                ConversationUtils.translateCurrentAbv(squire,true,progress)
            )
        } else {
            holder.hint.visibility = View.GONE
        }
    }

    fun setViewType(isGrid: Boolean) {
        this.isGrid = isGrid
        notifyDataSetChanged()
    }

    inner class MiniViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.squireImage);
        val nameSquire: TextView = itemView.findViewById(R.id.squireName);
        val sequence: TextView = itemView.findViewById(R.id.sequenceText);
        val hint: TextView = itemView.findViewById(R.id.hintText);
        val next: ImageView = itemView.findViewById(R.id.next);
        val previous: ImageView = itemView.findViewById(R.id.previous);
        val nextAccept: ImageView = itemView.findViewById(R.id.nextAccept);
        val seqRelative: RelativeLayout = itemView.findViewById(R.id.sequenceRelative);
        val infoButton: ImageButton = itemView.findViewById(R.id.infoButton);

        init {
            next.setOnClickListener(View.OnClickListener {
                val squire: Squire = squireList.get(adapterPosition)
                setSquireText(this,squire,squireProgressPreview.get(squire.id)!!+1)

            })
            previous.setOnClickListener(View.OnClickListener {
                val squire: Squire = squireList.get(adapterPosition)
                setSquireText(this,squire,squireProgressPreview.get(squire.id)!!-1)

            })
            nextAccept.setOnClickListener(View.OnClickListener {
                val squire: Squire = squireList.get(adapterPosition)
                val user:UserGeneral? = ShrdPrfsUtils.getUserData(context)
                user?.getCurrentCharacter()?.incSquireProgress(squire)
                ShrdPrfsUtils.saveUserData(context,user!!)
                YoYo.with(Techniques.FadeInRight)
                    .duration(700)
                    .playOn(seqRelative);
                setSquireText(this,squire,
                    user.getCurrentCharacter().squireProgress.get(squire.id)!!)
            })
            //TODO: Actually put a menu here instead of going to the fragment
            infoButton.setOnClickListener {
                val action =
                    MainFragmentDirections.actionNavHomeToSquireDetailsFragment(squireList.get(adapterPosition))
                Navigation.findNavController(it).navigate(action)
            }
        }

    }

}


